package com.bikemap.api.web.signup

/**
 * 회원가입 응답 데이터
 *
 * @property accessToken accessToken 인증 토큰
 * @property refreshToken refreshToken 인증 토큰
 */
class SignupResponse(
    val accessToken: String,
    val refreshToken: String,
)
