package com.bikemap.api.web.signin

import com.bikemap.api.application.auth.domain.Authentication
import com.bikemap.api.application.auth.service.SigninService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureRestDocs
@WebMvcTest(SigninController::class)
class SigninControllerTest(mockMvc: MockMvc) : DescribeSpec() {

    @MockkBean
    private lateinit var signinService: SigninService

    val objectMapper = ObjectMapper().registerModule(KotlinModule())
    val signinData =
        SigninRequest("test@email.com", "bikemap12345@@")

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
                    }.andDo {
                        handle(
                            document(
                                "signin-post",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                requestFields(
                                    fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                    fieldWithPath("password").type(JsonFieldType.STRING).description("패스워드"),
                                )
                            )
                        )
                    }
                }
            }

            context("유효하지 않은 로그인 정보가 주어지면") {
                listOf(
                    SigninRequest("", ""),
                    SigninRequest("abc", ""),
                    SigninRequest(signinData.email, ""),
                    SigninRequest(signinData.email, " "),
                    SigninRequest(signinData.email, "testaaa")
                ).forEach {
                    it("상태코드 400을 응답한다.") {
                        mockMvc.post("/signin") {
                            contentType = APPLICATION_JSON
                            content = objectMapper.writeValueAsString(it)
                        }.andExpect {
                            status { isBadRequest() }
                        }
                    }
                }
            }
        }
    }
}
