package com.bikemap.api.web.signup

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.application.auth.service.SignupService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureRestDocs
@WebMvcTest(SignupController::class)
class SignupControllerTest(mockMvc: MockMvc) : DescribeSpec() {

    @MockkBean
    private lateinit var signupService: SignupService

    val objectMapper = ObjectMapper().registerModule(KotlinModule())
    val signupData =
        SignupRequest("test@email.com", "bikemap12345@@", "홍길동", "남성", "1999-01-01")

    init {
        beforeEach {
            every {
                signupService.signup(
                    signupData.email,
                    signupData.password,
                    signupData.username,
                    signupData.gender,
                    signupData.birth
                )
            } returns Authentication("", "")
        }

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
                    }.andDo {
                        handle(
                            MockMvcRestDocumentation.document(
                                "signup-post",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                PayloadDocumentation.requestFields(
                                    fieldWithPath("email").type(JsonFieldType.STRING)
                                        .description("이메일"),
                                    fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("패스워드"),
                                    fieldWithPath("username").type(JsonFieldType.STRING)
                                        .description("이름"),
                                    fieldWithPath("gender").type(JsonFieldType.STRING)
                                        .description("성별"),
                                    fieldWithPath("birth").type(JsonFieldType.STRING)
                                        .description("생년월일"),
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}
