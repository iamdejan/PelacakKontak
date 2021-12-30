package com.example.pelacakkontak.ui.healthtest

import kotlinx.coroutines.flow.Flow

interface HealthTestRepository {
    fun list(): Flow<List<HealthTestResult>>
}
