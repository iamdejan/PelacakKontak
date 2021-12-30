package com.example.pelacakkontak.ui.healthtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pelacakkontak.databinding.CardHealthtestBinding
import com.example.pelacakkontak.util.FORMATTER

class HealthTestAdapter :
    ListAdapter<HealthTestResult, HealthTestAdapter.HealthTestViewHolder>(DiffCallback()) {

    inner class HealthTestViewHolder(private val binding: CardHealthtestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(testResult: HealthTestResult) {
            binding.apply {
                textViewHealthTestTestType.text = testResult.testType
                textViewHealthTestProvider.text = testResult.provider
                textViewHealthTestDate.text = FORMATTER.format(testResult.date)

                // TODO: set on click later (to open the URL)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HealthTestResult>() {
        override fun areItemsTheSame(
            oldItem: HealthTestResult,
            newItem: HealthTestResult
        ): Boolean = (oldItem == newItem)

        override fun areContentsTheSame(
            oldItem: HealthTestResult,
            newItem: HealthTestResult
        ): Boolean = (oldItem == newItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthTestViewHolder {
        val binding = CardHealthtestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HealthTestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthTestViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}
