package com.example.pelacakkontak.datastore

import kotlinx.coroutines.flow.Flow

interface TokenDataStoreRepository {

    fun getBearerToken(): Flow<String>

    suspend fun setBearerToken(value: String)

}
