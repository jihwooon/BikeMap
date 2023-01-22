package com.bikemap.api.web.signup

import com.bikemap.api.application.auth.service.SignupService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * 회원 가입 컨트롤러
 */
@RestController
class SignupController(
    val signupService: SignupService
) {
    /**
     * 회원 가입을 합니다.
     *
     * @param request 회원 가입 입력된 데이터
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun handleSignup(@Valid @RequestBody request: SignupRequest): SignupResponse {
        val authentication =
            signupService.signup(request.email, request.password, request.username, request.gender, request.birth)
        return SignupResponse(authentication.accessToken, authentication.refreshToken)
    }
}
