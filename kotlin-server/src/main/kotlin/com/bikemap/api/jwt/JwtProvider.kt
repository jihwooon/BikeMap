package com.bikemap.api.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtProvider(
    @Value("\${jwt.secret}") accessToken: String,
    @Value("\${jwt.secret}") refreshToken: String,
    @Value("\${jwt.accessToken.expiration}") accessExpiry: Long,
    @Value("\${jwt.refreshToken.expiration}") refreshExpiry: Long
) {
    private val accessTokenKey: Key
    private val refreshTokenKey: Key
    private val accessTokenValidityInSeconds: Long
    private val refreshTokenValidityInSeconds: Long

    init {
        this.accessTokenKey = Keys.hmacShaKeyFor(accessToken.toByteArray())
        this.refreshTokenKey = Keys.hmacShaKeyFor(refreshToken.toByteArray())
        this.accessTokenValidityInSeconds = accessExpiry
        this.refreshTokenValidityInSeconds = refreshExpiry
    }

    fun generateAccessToken(userId: Long): String = generateToken(
        userId, accessTokenKey, accessTokenValidityInSeconds
    )

    fun generateRefreshToken(userId: Long): String = generateToken(
        userId, refreshTokenKey, refreshTokenValidityInSeconds
    )

    private fun getExpirationDate(expiry: Long): Long = Date().time + expiry

    fun generateToken(userId: Long, key: Key, expiry: Long): String {
        return Jwts.builder()
            .claim("id", userId)
            .signWith(key)
            .setExpiration(Date(getExpirationDate(expiry)))
            .compact()
    }
}
