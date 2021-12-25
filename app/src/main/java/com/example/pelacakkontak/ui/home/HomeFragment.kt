package com.example.pelacakkontak.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentHomeBinding
import com.example.pelacakkontak.ui.BaseFragment
import com.example.pelacakkontak.util.TEST_LIST
import com.example.pelacakkontak.util.VACCINE_CERT_LIST
import com.example.pelacakkontak.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home), MenuItemAdapter.OnItemClickListener {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        val menuItemAdapter = MenuItemAdapter(loadData(), this)
        binding.apply {
            recyclerViewHomeMenu.apply {
                adapter = menuItemAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeEvents.collect { event ->
                when (event) {
                    is HomeViewModel.HomeEvent.Loading -> {
                        // TODO dejan: how to load a spinner in Android Studio?
                    }
                    is HomeViewModel.HomeEvent.GoToVaccineFragment -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToVaccineFragment()
                        findNavController().navigate(action)
                    }
                    is HomeViewModel.HomeEvent.GoToTestFragment -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToHealthTestFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }

    override fun onMenuClicked(menu: Menu) {
        viewModel.onMenuClicked(menu)
    }

    private fun loadData(): List<Menu> {
        return listOf(
            Menu(
                R.mipmap.ic_certificate_foreground,
                "Sertifikat vaksin",
                Color.rgb(5, 197, 1),
                VACCINE_CERT_LIST
            ),
            Menu(
                R.mipmap.ic_test_foreground,
                "Hasil tes",
                Color.rgb(218, 183, 0),
                TEST_LIST
            )
        )
    }

}
