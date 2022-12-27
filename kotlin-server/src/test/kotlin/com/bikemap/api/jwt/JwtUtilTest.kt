package com.bikemap.api.jwt

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class JwtUtilTest : DescribeSpec() {

    private val SECRET = "9l3hgt08wgcljwwx7itch6z1nwe236w3"
    private val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MX0.vMou9TCv70zWW2SGzPVB2R7go07DYfX_CKTyX2UEfDM"
    private val jwtUtil = JwtUtil(SECRET)

    init {
        describe("JwtUtil class") {
            val userId = 1L
            context("secret 정보가 주어지면") {
                it("accessToken를 반환한다.") {
                    val accessToken = jwtUtil.generateAccessToken(userId)

                    accessToken.shouldBe(ACCESS_TOKEN)
                }
            }
        }
    }
}
