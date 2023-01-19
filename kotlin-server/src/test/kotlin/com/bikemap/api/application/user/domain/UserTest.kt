package com.bikemap.api.application.user.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserTest : StringSpec({

    "회원 정보를 확인한다" {
        val id = 1L
        val username = "홍길동"
        val email = "test@email.com"
        val password = Password("bikemap12345@@", "ugSmbgmN6xpyeV6RtY6NGQ==")
        val gender = "남성"
        val birth = "1999-01-01"
        val user = User(id, username, email, password, gender, birth)

        assertSoftly(user) {
            user.id shouldBe 1
            user.username shouldBe "홍길동"
            user.email shouldBe "test@email.com"
            user.password shouldBe password
            user.gender shouldBe "남성"
            user.birth shouldBe "1999-01-01"
        }
    }
})
