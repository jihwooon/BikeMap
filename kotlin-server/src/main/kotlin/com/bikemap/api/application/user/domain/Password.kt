package com.bikemap.api.application.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Password(
    @Column
    val password: String = "",
    val salt: String = ""
)
