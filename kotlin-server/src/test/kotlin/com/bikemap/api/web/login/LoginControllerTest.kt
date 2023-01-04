package com.bikemap.api.web.login

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(LoginController::class)
class LoginControllerTest(mockMvc: MockMvc) : DescribeSpec() {

    init {

        describe("LoginController") {
            context("Post /Login을 요청하면") {
                it("login을 반환한다.") {
                    mockMvc.post("/login") {
                    }.andExpect {
                        status { isOk() }
                    }
                }
            }
        }
    }
}
