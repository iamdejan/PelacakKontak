package com.example.pelacakkontak.ui.vaccine

import android.os.Parcelable
import java.time.Instant
import kotlinx.parcelize.Parcelize

@Parcelize
data class VaccineCertificate(val fileUrl: String, val brand: String, val date: Instant) : Parcelable
