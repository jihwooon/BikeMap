package com.bikemap.api.jwt

import io.jsonwebtoken.security.SignatureException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class JwtParserTest : DescribeSpec() {

    private val ACCESS_TOKEN_SECRET = "9l3hgt08wgcljwwx7itch6z1nwe236w3"
    private val REFRESH_TOKEN_SECRET = "9l3hgt08wgcljwwx7itch6z1nwe23123"
    private val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MX0.vMou9TCv70zWW2SGzPVB2R7go07DYfX_CKTyX2UEfDM"
    private val REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MX0.KCWqyeQwpjw1z7bwG0yuPx4rTyRtYkPc6Pj0jYugmlM"
    private val WRONG_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MX0.vMou9TCv70zWW2SGzPVB2R7go07DYfX_CKTyX2UEf123"

    private val jwtParser = JwtParser(ACCESS_TOKEN_SECRET, REFRESH_TOKEN_SECRET)

    init {
        describe("JwtParser class") {
            context("accessToken 정보가 주어지면") {
                it("accessToken을 파싱한다.") {
                    val parsingAccessToken = jwtParser.parsingAccessToken(ACCESS_TOKEN)
                    val id = parsingAccessToken["id"].toString()

                    id.toLong() shouldBe 1L
                }
            }
            context("refreshToken 정보가 주어지면") {
                it("refreshToken을 파싱한다.") {
                    val parsingRefreshToken = jwtParser.parsingRefreshToken(REFRESH_TOKEN)
                    val id = parsingRefreshToken["id"].toString()

                    id.toLong() shouldBe 1L
                }
            }
            context("잘못된 accessToken이 주어지면") {
                it("SignatureException를 던진다.") {
                    val exception = shouldThrow<SignatureException> {
                        jwtParser.parsingAccessToken(WRONG_TOKEN)
                    }

                    exception.message shouldBe "잘못된 토큰 입니다."
                }
            }
            context("잘못된 refreshToken이 주어지면") {
                it("SignatureException를 던진다.") {
                    val exception = shouldThrow<SignatureException> {
                        jwtParser.parsingRefreshToken(WRONG_TOKEN)
                    }

                    exception.message shouldBe "잘못된 토큰 입니다."
                }
            }
        }
    }
}
