package com.bikemap.api.application.auth.service

import com.bikemap.api.application.KeyGenerator
import com.bikemap.api.application.user.domain.Email
import com.bikemap.api.application.user.domain.Gender
import com.bikemap.api.application.user.domain.Password
import com.bikemap.api.application.user.domain.User
import com.bikemap.api.application.user.domain.UserRepository
import com.bikemap.api.jwt.JwtProvider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDate

class SignupServiceTest : DescribeSpec() {
    private lateinit var signupService: SignupService
    private val userRepository: UserRepository = mockk()

    val ACCESS_TOKEN_EXPIRY: Long = 1000 * 60 * 30
    val REFRESH_TOKEN_EXPIRY: Long = 1000 * 60 * 60 * 24

    val keyGenerator: KeyGenerator = KeyGenerator()
    val jwtProvider: JwtProvider = JwtProvider(
        "9l3hgt08wgcljwwx7itch6z1nwe236w3",
        "9l3hgt08wgcljwwx7itch6z1nwe236w3",
        ACCESS_TOKEN_EXPIRY,
        REFRESH_TOKEN_EXPIRY
    )

    val email = "test@email.com"
    val username = "홍길동"
    val password = "bakemap12345"
    val salt = "ugSmbgmN6xpyeV6RtY6NGQ=="
    val gender = "남성"
    val birth = "1990-01-01"
    val user = User(
        id = 1L,
        email = Email(email),
        username = username,
        password = Password(password, salt),
        gender = Gender.getFromValue(gender),
        birth = LocalDate.parse(birth)
    )

    init {
        beforeEach {
            signupService = SignupService(
                jwtProvider,
                keyGenerator,
                userRepository
            )
        }

        describe("SignupService Class") {
            every {
                userRepository.save(any())
            } returns user

            it("Authentication을 반환한다.") {
                val authentication = signupService.signup(email, password, username, gender, birth)

                authentication.accessToken shouldNotBe null
                authentication.refreshToken shouldNotBe null
            }
        }
    }
}
