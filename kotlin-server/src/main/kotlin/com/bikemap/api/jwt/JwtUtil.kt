package com.bikemap.api.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtUtil(
    @Value("\${jwt.secret}") secret: String
) {
    private val accessTokenKey: Key

    init {
        this.accessTokenKey = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun generateAccessToken(userId: Long): String = generateToken(userId, accessTokenKey)

    fun generateToken(userId: Long, key: Key): String {
        return Jwts.builder()
            .claim("id", userId)
            .signWith(key)
            .compact()
    }
}
