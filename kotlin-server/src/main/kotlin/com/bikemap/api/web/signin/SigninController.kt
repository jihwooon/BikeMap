package com.bikemap.api.web.signin

import com.bikemap.api.application.auth.service.SigninService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class SigninController(
    private val signinService: SigninService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signin")
    fun handleSignin(@Valid @RequestBody request: SigninRequest): SigninResponse {
        val authentication = signinService.signin(request.email, request.password)
        return SigninResponse(
            authentication.accessToken,
            authentication.refreshToken
        )
    }
}
