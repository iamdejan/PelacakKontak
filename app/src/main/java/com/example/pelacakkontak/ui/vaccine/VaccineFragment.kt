package com.example.pelacakkontak.ui.vaccine

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentVaccineBinding
import com.example.pelacakkontak.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant

@AndroidEntryPoint
class VaccineFragment : BaseFragment(R.layout.fragment_vaccine) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentVaccineBinding.bind(view)
        val vaccineCertAdapter = VaccineCertAdapter(loadData())
        binding.apply {
            recyclerViewVaccineCertificates.apply {
                adapter = vaccineCertAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun loadData(): List<VaccineCertificate> {
        // TODO: load from API in the future
        return listOf(
            VaccineCertificate(
                "https://images.bisnis-cdn.com/posts/2021/08/10/1428103/serifikat-vaksinasi.jpg",
                "Lorem ipsum",
                Instant.now()
            )
        )
    }
}
