package com.bikemap.api.web.login

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController() {

    @PostMapping("/login")
    fun handleLogin(): String {
        return "login"
    }
}
