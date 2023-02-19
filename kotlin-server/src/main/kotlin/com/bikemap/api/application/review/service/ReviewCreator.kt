package com.bikemap.api.application.review.service

import com.bikemap.api.application.review.domain.Review
import com.bikemap.api.application.review.domain.ReviewRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

/**
 * 후기 게시판 생성 서비스
 */
@Service
class ReviewCreator(
    private val repository: ReviewRepository
) {

    /**
     * 후기 게시물을 생성합니다.
     *
     * @param title 후기 제목
     * @param content 후기 내용
     */
    @Transactional
    fun create(title: String, content: String): Review {
        val review = Review(title = title, content = content)

        return repository.save(review)
    }
}
