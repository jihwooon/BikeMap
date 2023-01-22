package com.bikemap.api.application.auth.domain

/**
 * Authentication 객체
 *
 * @property accessToken accessToken 인증 토큰
 * @property refreshToken refreshToken 인증 토큰
 */
class Authentication(
    val accessToken: String,
    val refreshToken: String
)
