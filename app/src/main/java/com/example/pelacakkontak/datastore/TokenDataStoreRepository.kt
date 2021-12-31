package com.example.pelacakkontak.datastore

import androidx.lifecycle.LiveData

interface TokenDataStoreRepository {

    fun getBearerToken(): LiveData<String>

    suspend fun setBearerToken(value: String)

}
