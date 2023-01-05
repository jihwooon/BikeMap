package com.bikemap.api.web.signin

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.application.auth.service.SigninService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(SigninController::class)
class SigninControllerTest(mockMvc: MockMvc) : DescribeSpec() {

    @MockkBean
    private lateinit var signinService: SigninService

    val objectMapper = ObjectMapper().registerModule(KotlinModule())
    val signinData =
        SigninRequest("test@email.com", "1234")

    init {
        beforeEach {
            every {
                signinService.signin(signinData.email, signinData.password)
            } returns Authentication("", "")
        }

        describe("SigninController") {
            context("Post /signin을 요청하면") {
                it("signin을 반환한다.") {
                    mockMvc.post("/signin") {
                        contentType = MediaType.APPLICATION_JSON
                        content = objectMapper.writeValueAsString(signinData)
                    }.andExpect {
                        status { isCreated() }
                        jsonPath("$.accessToken") { isString() }
                        jsonPath("$.refreshToken") { isString() }
                    }
                }
            }
        }
    }
}
