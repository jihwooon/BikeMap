package com.bikemap.api.application.auth.service

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.application.user.domain.UserRepository
import com.bikemap.api.application.user.execption.UserNotFoundException
import com.bikemap.api.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class SigninService(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository
) {

    fun signin(email: String, password: String): Authentication {
        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException(email)

        return Authentication(
            jwtProvider.generateAccessToken(user.id),
            jwtProvider.generateRefreshToken(user.id)
        )
    }
}
