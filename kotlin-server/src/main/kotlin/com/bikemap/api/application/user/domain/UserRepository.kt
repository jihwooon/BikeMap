package com.bikemap.api.application.user.domain

import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, Long> {

    /**
     * 사용자 이메일을 찾습니다.
     *
     * @param email 사용자 이메일
     * @return 사용자를 찾으면 User 객체 아니면 null
     */
    fun findByEmail(email: String): User?

    fun save(user: User): User
}
