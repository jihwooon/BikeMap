package com.bikemap.api.web.signup

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(SignupController::class)
class SignupControllerTest(mockMvc: MockMvc) : DescribeSpec() {

    val objectMapper = ObjectMapper().registerModule(KotlinModule())
    val signupData =
        SignupRequest("test@email.com", "bikemap12345@@", "홍길동", "남성", "1999-01-01")

    init {
        describe("SignupController") {
            context("POST/ signup을 요청하면") {
                it("accessToken과 refreshToken을 반환한다") {
                    mockMvc.post("/signup") {
                        contentType = MediaType.APPLICATION_JSON
                        content = objectMapper.writeValueAsString(signupData)
                    }.andExpect {
                        status {
                            isCreated()
                            jsonPath("$.accessToken") { isString() }
                            jsonPath("$.refreshToken") { isString() }
                        }
                    }
                }
            }
        }
    }
}
