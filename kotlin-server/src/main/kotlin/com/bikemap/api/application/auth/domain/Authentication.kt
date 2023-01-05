package com.bikemap.api.application.auth.domain

data class Authentication(
    val accessToken: String,
    val refreshToken: String
)
