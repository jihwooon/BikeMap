package com.bikemap.api.jwt

import com.bikemap.api.auth.jwt.JwtProvider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class JwtProviderTest : DescribeSpec() {

    private val ACCESS_TOKEN_SECRET = "9l3hgt08wgcljwwx7itch6z1nwe236w3"
    private val REFRESH_TOKEN_SECRET = "9l3hgt08wgcljwwx7itch6z1nwe23123"

    private val ACCESS_TOKEN_EXPIRY: Long = 1000 * 60 * 30
    private val REFRESH_TOKEN_EXPIRY: Long = 1000 * 60 * 60 * 24

    private val jwtProvider =
        JwtProvider(ACCESS_TOKEN_SECRET, REFRESH_TOKEN_SECRET, ACCESS_TOKEN_EXPIRY, REFRESH_TOKEN_EXPIRY)

    init {
        describe("JwtUtil class") {
            val userId = 1L
            context("accessSecret 정보가 주어지면") {
                it("accessToken을 반환한다.") {
                    val accessToken = jwtProvider.generateAccessToken(userId)

                    accessToken shouldNotBe null
                }
            }
            context("refreshSecret 정보가 주어지면") {
                it("refreshToken을 반환한다.") {
                    val refreshToken = jwtProvider.generateRefreshToken(userId)

                    refreshToken shouldNotBe null
                }
            }
        }
    }
}
