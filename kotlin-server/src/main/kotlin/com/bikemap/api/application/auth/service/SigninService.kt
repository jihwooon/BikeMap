package com.bikemap.api.application.auth.service

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class SigninService(
    private val jwtProvider: JwtProvider
) {

    fun signin(email: String, password: String): Authentication {
        return Authentication(
            jwtProvider.generateAccessToken(1),
            jwtProvider.generateRefreshToken(1)
        )
    }
}
