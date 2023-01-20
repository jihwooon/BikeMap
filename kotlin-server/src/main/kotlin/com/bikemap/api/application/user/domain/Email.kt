package com.bikemap.api.application.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Email(
    @Column
    val email: String = "",
)
