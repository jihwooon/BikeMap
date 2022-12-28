package com.bikemap.api.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
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

    fun generateToken(userId: Long, key: Key): String {
        return Jwts.builder()
            .claim("id", userId)
            .signWith(key)
            .compact()
    }
}
