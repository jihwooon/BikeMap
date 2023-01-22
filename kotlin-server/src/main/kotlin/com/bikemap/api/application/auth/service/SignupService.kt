package com.bikemap.api.application.auth.service

import com.bikemap.api.application.KeyGenerator
import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.application.user.domain.Email
import com.bikemap.api.application.user.domain.Gender
import com.bikemap.api.application.user.domain.Password
import com.bikemap.api.application.user.domain.User
import com.bikemap.api.application.user.domain.UserRepository
import com.bikemap.api.jwt.JwtProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

/**
 * 회원 가입 서비스
 */
@Transactional
@Service
class SignupService(
    private val jwtProvider: JwtProvider,
    private val keyGenerator: KeyGenerator,
    private val userRepository: UserRepository
) {

    /**
     * 회원 정보를 생성합니다.
     *
     * @param email 이메일
     * @param password 비밀번호
     * @param username 회원 이름
     * @param gender 회원 성별
     * @param birth 회원 생년월일
     * @return AccessToken과 RefreshToken을 반환합니다.
     */
    fun signup(email: String, password: String, username: String, gender: String, birth: String): Authentication {
        val salt = keyGenerator.generateSalt()
        val hashedPassword = keyGenerator.hash(password, salt)

        val user = userRepository.save(
            User(
                email = Email(email),
                password = Password(hashedPassword, salt),
                username = username,
                gender = Gender.getFromValue(gender),
                birth = LocalDate.parse(birth)
            )
        )

        return Authentication(
            jwtProvider.generateAccessToken(user.id),
            jwtProvider.generateRefreshToken(user.id)
        )
    }
}
