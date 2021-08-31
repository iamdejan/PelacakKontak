package com.example.pelacakkontak.ui.healthtest

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentHealthtestBinding
import com.example.pelacakkontak.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.temporal.ChronoUnit

@AndroidEntryPoint
class HealthTestFragment : BaseFragment(R.layout.fragment_healthtest) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHealthtestBinding.bind(view)
        val healthTestAdapter = HealthTestAdapter(loadData())
        binding.apply {
            recyclerViewHtResults.apply {
                adapter = healthTestAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun loadData(): List<HealthTestResult> {
        // TODO: load from API in the future
        return listOf(
            HealthTestResult(
                "https://images.bisnis-cdn.com/posts/2021/08/10/1428103/serifikat-vaksinasi.jpg",
                "Swab PCR",
                "RS Medistra",
                Instant.now()
            ),
            HealthTestResult(
                "https://images.bisnis-cdn.com/posts/2021/08/10/1428103/serifikat-vaksinasi.jpg",
                "Swab PCR",
                "GSI Lab Bintaro",
                Instant.now().plus(3 * 30, ChronoUnit.DAYS)
            )
        )
    }
}
