package com.bikemap.api.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key

/**
 * Jwt Parser 객체
 */
@Component
class JwtParser(
    @Value("\${jwt.secret}") accessToken: String,
    @Value("\${jwt.secret}") refreshToken: String
) {
    private val accessTokenKey: Key
    private val refreshTokenKey: Key

    init {
        this.accessTokenKey = Keys.hmacShaKeyFor(accessToken.toByteArray())
        this.refreshTokenKey = Keys.hmacShaKeyFor(refreshToken.toByteArray())
    }

    fun parsingAccessToken(token: String): Claims = parsingToken(token, accessTokenKey)
    fun parsingRefreshToken(token: String): Claims = parsingToken(token, refreshTokenKey)

    /**
     * Token을 분석하다.
     *
     * @param token JWT 인증 토큰
     * @param key JWT 키
     */
    fun parsingToken(token: String, key: Key): Claims {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            throw SignatureException("잘못된 토큰 입니다.")
        }
    }
}
