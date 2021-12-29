package com.example.pelacakkontak.ui.healthtest

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class HealthTestRepositoryImpl @Inject constructor() : HealthTestRepository {

    override fun list(): Flow<List<HealthTestResult>> {
        return flow {
            delay(2000L) // TODO: remove later, this is only for trials
            emit(
                listOf(
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
            )
        }
    }
}
