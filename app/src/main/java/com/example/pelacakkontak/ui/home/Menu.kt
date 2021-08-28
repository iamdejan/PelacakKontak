package com.example.pelacakkontak.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(val resourceId: Int, val title: String, val backgroundColor: Int) : Parcelable
