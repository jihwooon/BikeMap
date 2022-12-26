package com.bikemap.api.jwt

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Assertions.*

class JwtUtilTest : DescribeSpec() {

    val SECRET = "9l3hgt08wgcljwwx7itch6z1nwe236w3"
    val jwtUtil = JwtUtil(SECRET)

    init {
        describe("JwtUtil class") {
            val userId = 1L
            context("secret 정보가 주어지면") {
                it("accessToken를 반환한다.") {
                    val accessToken = jwtUtil.generateAccessToken(userId)

                    accessToken.shouldNotBe(null);
                }
            }

        }
    }
}
