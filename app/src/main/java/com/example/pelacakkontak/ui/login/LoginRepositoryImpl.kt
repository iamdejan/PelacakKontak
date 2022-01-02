package com.example.pelacakkontak.ui.login

import com.example.pelacakkontak.api.AuthApi
import com.example.pelacakkontak.api.requests.LoginRequest
import com.example.pelacakkontak.api.responses.LoginResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val authApi: AuthApi) : LoginRepository {

    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        return try {
            authApi.login(LoginRequest(email, password))
        } catch (e: SocketTimeoutException) {
            val gson = Gson();
            val failResponse = LoginResponse(null, false);
            Response.error(404, ResponseBody.create(null, gson.toJson(failResponse)))
        }
    }
}
