package com.example.pelacakkontak.ui.login

import com.example.pelacakkontak.api.responses.LoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse>
}
