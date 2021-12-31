package com.example.pelacakkontak.api

import com.example.pelacakkontak.api.requests.LoginRequest
import com.example.pelacakkontak.api.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    companion object {
        const val BASE_URL = "http://peduli.xyz:8888"
    }

    @POST("/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
