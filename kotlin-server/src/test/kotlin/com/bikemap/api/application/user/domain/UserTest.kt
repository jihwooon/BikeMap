package com.bikemap.api.application.user.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class UserTest : StringSpec({

    "회원 정보를 확인한다" {
        val id = 1L
        val username = "홍길동"
        val email = Email("test@email.com")
        val password = Password("bikemap12345@@", "ugSmbgmN6xpyeV6RtY6NGQ==")
        val gender = Gender.MALE
        val birth = LocalDate.of(1990, 1, 1)
        val user = User(id, username, email, password, gender, birth)

        assertSoftly(user) {
            user.id shouldBe 1
            user.username shouldBe "홍길동"
            user.email shouldBe email
            user.password shouldBe password
            user.gender shouldBe gender
            user.birth shouldBe birth
        }
    }
})
