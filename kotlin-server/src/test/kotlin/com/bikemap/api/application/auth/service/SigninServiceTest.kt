package com.bikemap.api.application.auth.service

import com.bikemap.api.application.user.domain.User
import com.bikemap.api.application.user.domain.UserRepository
import com.bikemap.api.jwt.JwtProvider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk

class SigninServiceTest : DescribeSpec() {
    private lateinit var signinService: SigninService

    val ACCESS_TOKEN_EXPIRY: Long = 1000 * 60 * 30
    val REFRESH_TOKEN_EXPIRY: Long = 1000 * 60 * 60 * 24

    val userRepository: UserRepository = mockk()
    val jwtProvider: JwtProvider = JwtProvider(
        "9l3hgt08wgcljwwx7itch6z1nwe236w3",
        "9l3hgt08wgcljwwx7itch6z1nwe236w3",
        ACCESS_TOKEN_EXPIRY,
        REFRESH_TOKEN_EXPIRY
    )

    val email = "test@email.com"
    val password = "bakemap12345"
    val user = User(
        id = 1L,
        email = email,
        password = password
    )

    init {
        beforeEach {
            this.signinService = SigninService(jwtProvider, userRepository)
        }
        describe("SigninService Class") {
            every {
                userRepository.findByEmail("test@email.com")
            } returns user

            it("Authentication을 반환한다.") {
                val authentication = signinService.signin("test@email.com", "1234")

                authentication.accessToken shouldNotBe null
                authentication.refreshToken shouldNotBe null
            }
        }
    }
}
