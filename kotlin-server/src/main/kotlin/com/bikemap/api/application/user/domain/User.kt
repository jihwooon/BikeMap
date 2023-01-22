package com.bikemap.api.application.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

/**
 * User 객체
 */
@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    val username: String = "",

    @Column(nullable = false)
    @Embedded
    val email: Email = Email(),

    @Column(nullable = false)
    @Embedded
    val password: Password = Password(),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val gender: Gender = Gender.MALE,

    @Column(nullable = false)
    val birth: LocalDate = LocalDate.now(),
)
