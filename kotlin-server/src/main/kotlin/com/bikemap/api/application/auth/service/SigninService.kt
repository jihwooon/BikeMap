package com.bikemap.api.application.auth.service

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.application.user.domain.UserRepository
import com.bikemap.api.application.user.execption.UserNotFoundException
import com.bikemap.api.jwt.JwtProvider
import org.springframework.stereotype.Service

/**
 * 로그인 서비스
 */
@Service
class SigninService(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository
) {

    /**
     * 로그인을 합니다.
     *
     * @param email 이메일
     * @param password 비밀번호
     * @return AccessToken과 RefreshToken을 반환합니다.
     */
    fun signin(email: String, password: String): Authentication {
        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException(email)

        return Authentication(
            jwtProvider.generateAccessToken(user.id),
            jwtProvider.generateRefreshToken(user.id)
        )
    }
}
