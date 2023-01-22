package com.bikemap.api.application.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

/**
 * Email 객체
 */
@Embeddable
class Email(
    @Column
    val email: String = "",
)
