package com.example.pelacakkontak.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentHomeBinding
import com.example.pelacakkontak.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        val menuItemAdapter = MenuItemAdapter(loadData())
        binding.apply {
            listViewHomeMenu.apply {
                adapter = menuItemAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

        }
    }

    private fun loadData(): List<Menu> {
        return listOf(
            Menu(R.mipmap.ic_certificate_foreground, "Sertifikat vaksin", Color.rgb(5, 197, 1)),
            Menu(R.mipmap.ic_test_foreground, "Hasil tes", Color.rgb(218, 183, 0))
        )
    }

}
