package com.example.pelacakkontak.ui.login

import com.example.pelacakkontak.api.AuthApi
import com.example.pelacakkontak.api.requests.LoginRequest
import com.example.pelacakkontak.api.responses.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val authApi: AuthApi): LoginRepository {

    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        return authApi.login(LoginRequest(email, password))
    }
}
