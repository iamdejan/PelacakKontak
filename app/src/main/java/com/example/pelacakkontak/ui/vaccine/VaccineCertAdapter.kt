package com.example.pelacakkontak.ui.vaccine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pelacakkontak.databinding.CardVaccineBinding
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val FORMATTER: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("Asia/Jakarta"))

class VaccineCertAdapter(vaccineCertList: List<VaccineCertificate>) :
    ListAdapter<VaccineCertificate, VaccineCertAdapter.VaccineViewHolder>(DiffCallback()) {

    // TODO: this might be deleted in the future
    init {
        submitList(vaccineCertList)
    }

    inner class VaccineViewHolder(private val binding: CardVaccineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vaccineCert: VaccineCertificate, position: Int) {
            binding.apply {
                textViewVaccineTitle.text = "Vaksin #${position + 1}"
                textViewVaccineBrand.text = vaccineCert.brand
                textViewVaccineDate.text = FORMATTER.format(vaccineCert.date)

                // TODO: set on click later (to open the URL)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<VaccineCertificate>() {
        override fun areItemsTheSame(
            oldItem: VaccineCertificate,
            newItem: VaccineCertificate
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: VaccineCertificate,
            newItem: VaccineCertificate
        ): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineViewHolder {
        val binding = CardVaccineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VaccineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VaccineViewHolder, position: Int) {
        val vaccineCert = getItem(position)
        holder.bind(vaccineCert, position)
    }

}
