package com.bikemap.api.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class KeyGeneratorTest : DescribeSpec() {
    val keyGenerator = KeyGenerator()

    init {
        describe("generateSalt 메서드") {
            it("임의의 salt를 반환한다") {
                val salt = keyGenerator.generateSalt()

                salt shouldNotBe null
            }
        }

        describe("hash 메서드") {
            it("임의의 salt를 반환한다") {
                val rawPassword = "bikemap12345@@"
                val salt = keyGenerator.generateSalt()
                val hash = keyGenerator.hash(rawPassword, salt)

                hash shouldNotBe rawPassword
            }

            context("같은 rawPassword가 주어져도 salt가 다르면") {
                val rawPassword = "bikemap12345@@"
                it("해시 값은 다르다") {
                    val salt1 = keyGenerator.generateSalt()
                    val hash1 = keyGenerator.hash(rawPassword, salt1)

                    val salt2 = keyGenerator.generateSalt()
                    val hash2 = keyGenerator.hash(rawPassword, salt2)

                    hash1 shouldNotBe hash2
                }
            }
        }
    }
}
