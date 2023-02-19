package com.bikemap.api.application.review.domain

import org.springframework.data.repository.Repository

interface ReviewRepository : Repository<Review, Long> {

    /**
     * 후기 게시판 정보를 저장합니다.
     *
     * @param review review 객체
     */
    fun save(review: Review): Review
}
