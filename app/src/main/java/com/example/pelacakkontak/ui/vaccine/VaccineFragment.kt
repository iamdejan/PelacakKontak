package com.example.pelacakkontak.ui.vaccine

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentVaccineBinding
import com.example.pelacakkontak.ui.BaseFragment
import com.example.pelacakkontak.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VaccineFragment : BaseFragment(R.layout.fragment_vaccine) {
    private val viewModel: VaccineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentVaccineBinding.bind(view)
        val vaccineCertAdapter = VaccineCertAdapter()
        binding.apply {
            recyclerViewVaccineCertificates.apply {
                adapter = vaccineCertAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.load()
        viewModel.vaccineCerts.observe(viewLifecycleOwner) {
            vaccineCertAdapter.submitList(it)
            viewModel.endLoad()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.vaccineEvents.collect { event ->
                when (event) {
                    is VaccineViewModel.VaccineEvent.Loading -> {
                        binding.progressBarVaccineLoader.isVisible = true

                        binding.recyclerViewVaccineCertificates.isVisible = false
                        binding.textViewVaccineTitle.isVisible = false
                    }
                    is VaccineViewModel.VaccineEvent.LoadingFinished -> {
                        binding.progressBarVaccineLoader.isVisible = false

                        binding.recyclerViewVaccineCertificates.isVisible = true
                        binding.textViewVaccineTitle.isVisible = true
                    }
                }.exhaustive
            }
        }
    }

}
