package com.bikemap.api.web.review

import com.bikemap.api.application.review.domain.Review
import com.bikemap.api.application.review.service.ReviewCreator
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
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@AutoConfigureRestDocs
@WebMvcTest(ReviewCreateController::class)
class ReviewCreateControllerTest(mockMvc: MockMvc) : DescribeSpec() {

    @MockkBean
    private lateinit var reviewCreator: ReviewCreator

    val objectMapper = ObjectMapper().registerModule(KotlinModule())
    val request = ReviewCreateController.RequestData("후기 게시판 제목", "후기 게시판 내용")

    val review = Review(1L, "", "")

    init {
        beforeEach {
            every {
                reviewCreator.create(request.title, request.content)
            } returns review
        }

        describe("ReviewCreateController") {
            context("Post /reviews를 요청하면") {
                it("204 NoContent를 응답한다.") {
                    mockMvc.post("/reviews") {
                        contentType = MediaType.APPLICATION_JSON
                        content = objectMapper.writeValueAsString(request)
                    }.andExpect {
                        status { isCreated() }
                    }.andDo {
                        handle(
                            MockMvcRestDocumentation.document(
                                "review-post",
                                preprocessRequest(Preprocessors.prettyPrint()),
                                preprocessResponse(Preprocessors.prettyPrint()),
                                requestFields(
                                    fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("후기 제목"),
                                    fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("후기 내용"),
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}
