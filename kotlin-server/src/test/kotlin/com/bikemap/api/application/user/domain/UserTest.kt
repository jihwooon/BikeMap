package com.bikemap.api.application.user.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserTest : StringSpec({

    "회원 정보를 확인한다" {
        val user = User(1, "홍길동", "test@email.com", "1234", "남성", "1999-01-01")

        assertSoftly(user) {
            user.id shouldBe 1
            user.username shouldBe "홍길동"
            user.email shouldBe "test@email.com"
            user.password shouldBe "1234"
            user.gender shouldBe "남성"
            user.birth shouldBe "1999-01-01"
        }
    }
})
