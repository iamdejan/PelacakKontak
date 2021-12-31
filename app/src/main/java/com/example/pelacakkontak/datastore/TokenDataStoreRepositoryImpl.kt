package com.example.pelacakkontak.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenDataStoreRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    TokenDataStoreRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_token")

    private val preferenceFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.BEARER_TOKEN].toString()
    }

    override fun getBearerToken(): Flow<String> = preferenceFlow

    override suspend fun setBearerToken(value: String) {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.BEARER_TOKEN] = value
        }
    }

    private object PreferenceKeys {
        val BEARER_TOKEN = stringPreferencesKey("token")
    }
}
