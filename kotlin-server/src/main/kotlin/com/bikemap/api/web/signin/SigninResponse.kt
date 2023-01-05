package com.bikemap.api.web.signin

data class SigninResponse(
    val accessToken: String,
    val refreshToken: String,
)
