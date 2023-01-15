package com.bikemap.api.application.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String = "",

    val email: String = "",

    val password: String = "",

    val gender: String = "",

    val birth: String = "",
)
