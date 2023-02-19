package com.bikemap.api.application.review.service

import com.bikemap.api.application.review.domain.Review
import com.bikemap.api.application.review.domain.ReviewRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ReviewCreatorTest : DescribeSpec() {
    private lateinit var reviewCreator: ReviewCreator

    val reviewRepository: ReviewRepository = mockk()

    val title = "후기 제목"
    val content = "후기 내용"

    val review = Review(
        id = 1L,
        title = title,
        content = content
    )

    init {
        beforeEach {
            this.reviewCreator = ReviewCreator(reviewRepository)
        }
        describe("ReviewCreator Class") {
            every {
                reviewRepository.save(any())
            } returns review
        }

        it("Review를 반환한다.") {
            val reviews = reviewCreator.create(title, content)

            reviews.title shouldBe title
            reviews.content shouldBe content
        }
    }
}
