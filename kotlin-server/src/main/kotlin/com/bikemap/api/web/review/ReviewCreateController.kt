package com.bikemap.api.web.review

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * 후기 게시판 컨트롤러
 */
@RestController
class ReviewCreateController {
    /**
     * 후기 게시판 생성 요청을 처리합니다.
     *
     * @param request 게시판 데이터
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reviews")
    fun createReview(@RequestBody request: RequestData) {
    }

    /**
     * 후기 게시판 데이터
     *
     * @property title 후기 제목
     * @property content 후기 내용
     */
    class RequestData(
        val title: String,
        val content: String
    )
}
