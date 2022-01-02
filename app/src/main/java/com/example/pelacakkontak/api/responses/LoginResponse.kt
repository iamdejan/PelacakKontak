package com.example.pelacakkontak.api.responses

data class LoginResponse(val jwtToken: JwtToken?, val success: Boolean)

data class JwtToken(val token: String)
