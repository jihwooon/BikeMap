package com.bikemap.api.application.user.execption

/**
 * 회원을 찾지 못하는 경우 예외
 */
class UserNotFoundException(email: String?, id: Long?) : RuntimeException() {

    constructor(email: String) : this(email, 0L)

    constructor(id: Long) : this(null, id)
}
