package com.bikemap.api.application

import org.bouncycastle.crypto.generators.SCrypt
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_16
import java.security.SecureRandom
import java.util.Base64

/**
 * 해시 암호 생성기
 */
@Component
class KeyGenerator {
    private val secureRandom = SecureRandom()

    private val SCYPT_COST = 16384
    private val SCRYPT_BLOXK_SIZE = 8
    private val SCRYPT_PARALLELISM = 1
    private val KEY_LENGTH = 20
    private val SALT_LENGTH = 16

    /**
     * 해시암호를 임의의 솔트로 반환한다
     *
     * @return 16 bytes 랜덤 솔트
     */
    fun generateSalt(): String {
        val bytes = ByteArray(SALT_LENGTH)
        secureRandom.nextBytes(bytes)
        return Base64.getEncoder().encodeToString(bytes)
    }

    /**
     * 솔트와 해시 암호를 제공한다
     *
     * @return 솔트된 해시 암호
     */
    fun hash(password: String, salt: String): String {
        val passwordBytes = password.toByteArray(UTF_16)
        val saltBytes = salt.toByteArray(UTF_16)
        return Base64.getEncoder().encodeToString(
            SCrypt.generate(
                passwordBytes,
                saltBytes,
                SCYPT_COST,
                SCRYPT_BLOXK_SIZE,
                SCRYPT_PARALLELISM,
                KEY_LENGTH
            )
        )
    }
}
