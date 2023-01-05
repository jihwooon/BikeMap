package com.bikemap.api.application.auth.service

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.jwt.JwtProvider
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import io.mockk.every

class SigninServiceTest : DescribeSpec() {
    private val ACCESS_TOKEN_EXPIRY: Long = 1000 * 60 * 30
    private val REFRESH_TOKEN_EXPIRY: Long = 1000 * 60 * 60 * 24

    @MockkBean
    private lateinit var signinService: SigninService

    private val jwtProvider: JwtProvider = JwtProvider(
        "9l3hgt08wgcljwwx7itch6z1nwe236w3",
        "9l3hgt08wgcljwwx7itch6z1nwe236w3",
        ACCESS_TOKEN_EXPIRY,
        REFRESH_TOKEN_EXPIRY
    )

    init {
        describe("SigninService Class") {
            every {
                signinService.signin(any(), any())
            } returns Authentication("", "")

            it("Authentication을 반환한다.") {
                val authentication = signinService.signin("test@email.com", "1234")

                authentication.accessToken shouldNotBe null
                authentication.refreshToken shouldNotBe null
            }
        }
    }
}
