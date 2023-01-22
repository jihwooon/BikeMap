package com.bikemap.api.web.signin

/**
 * 로그인 응답 데이터
 *
 * @property accessToken accessToken 인증 토큰
 * @property refreshToken refreshToken 인증 토큰
 */
data class SigninResponse(
    val accessToken: String,
    val refreshToken: String,
)
