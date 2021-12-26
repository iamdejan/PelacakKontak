package com.example.pelacakkontak.ui.vaccine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class VaccineRepositoryImpl @Inject constructor() : VaccineRepository {

    override fun list(): Flow<List<VaccineCertificate>> {
        return flow {
            delay(2000L) // TODO dejan: only for trials
            emit(
                listOf(
                    VaccineCertificate(
                        "https://images.bisnis-cdn.com/posts/2021/08/10/1428103/serifikat-vaksinasi.jpg",
                        "Oxford-AstraZeneca",
                        Instant.now()
                    ),
                    VaccineCertificate(
                        "https://images.bisnis-cdn.com/posts/2021/08/10/1428103/serifikat-vaksinasi.jpg",
                        "Pfizer-BioNTech",
                        Instant.now().plus(3 * 30, ChronoUnit.DAYS)
                    )
                )
            )
        }
    }
}
