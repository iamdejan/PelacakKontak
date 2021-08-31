package com.example.pelacakkontak.ui.healthtest

import android.os.Parcelable
import java.time.Instant
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthTestResult(val fileUrl: String, val testType: String, val provider: String, val date: Instant): Parcelable
