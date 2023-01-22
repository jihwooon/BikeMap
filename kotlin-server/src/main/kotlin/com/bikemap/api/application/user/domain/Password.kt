package com.bikemap.api.application.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

/**
 * Password 객체
 */
@Embeddable
class Password(
    @Column
    val password: String = "",
    val salt: String = ""
)
