package com.bikemap.api.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtUtil(
    @Value("\${jwt.secret.accessToken}") accessToken: String,
    @Value("\${jwt.secret.refreshToken}") refreshToken: String
) {
    private val accessTokenKey: Key
    private val refreshTokenKey: Key

    init {
        this.accessTokenKey = Keys.hmacShaKeyFor(accessToken.toByteArray())
        this.refreshTokenKey = Keys.hmacShaKeyFor(refreshToken.toByteArray())
    }

    fun generateAccessToken(userId: Long): String = generateToken(userId, accessTokenKey)
    fun generateRefreshToken(userId: Long): String = generateToken(userId, refreshTokenKey)

    fun parsingAccessToken(token: String): Claims = parsingToken(token, accessTokenKey)
    fun parsingRefreshToken(token: String): Claims = parsingToken(token, refreshTokenKey)

    fun generateToken(userId: Long, key: Key): String {
        return Jwts.builder()
            .claim("id", userId)
            .signWith(key)
            .compact()
    }

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
