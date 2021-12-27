package com.example.pelacakkontak.ui.vaccine

import kotlinx.coroutines.flow.Flow

interface VaccineRepository {

    fun list(): Flow<List<VaccineCertificate>>
}
