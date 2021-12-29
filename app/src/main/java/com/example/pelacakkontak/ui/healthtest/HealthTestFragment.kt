package com.example.pelacakkontak.ui.healthtest

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentHealthtestBinding
import com.example.pelacakkontak.ui.BaseFragment
import com.example.pelacakkontak.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HealthTestFragment : BaseFragment(R.layout.fragment_healthtest) {

    private val viewModel: HealthTestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHealthtestBinding.bind(view)
        val healthTestAdapter = HealthTestAdapter()
        binding.apply {
            recyclerViewHtResults.apply {
                adapter = healthTestAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.load()
        viewModel.healthTests.observe(viewLifecycleOwner) {
            healthTestAdapter.submitList(it)
            viewModel.endLoad()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.healthEvents.collect { event ->
                when (event) {
                    is HealthTestViewModel.HealthTestEvent.Loading -> {
                        binding.recyclerViewHtResults.isVisible = false
                        binding.textViewHtTitle.isVisible = false

                        binding.progressBarHtLoader.isVisible = true
                    }
                    is HealthTestViewModel.HealthTestEvent.LoadingFinished -> {
                        binding.recyclerViewHtResults.isVisible = true
                        binding.textViewHtTitle.isVisible = true

                        binding.progressBarHtLoader.isVisible = false
                    }
                }.exhaustive
            }
        }
    }
}
