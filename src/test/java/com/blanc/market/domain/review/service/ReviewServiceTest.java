package com.blanc.market.domain.review.service;

import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.review.dto.ReviewRequest;
import com.blanc.market.domain.review.dto.ReviewResponse;
import com.blanc.market.domain.review.entity.Review;
import com.blanc.market.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    private Review testReview;
    private Product testProduct;


    @BeforeEach
    void setUp() {
        testReview = reviewRepository.save(Review.builder()
                .content("Test Review")
                .build()
        );

        testProduct = productRepository.save(Product.builder()
                .name("Test Product")
                .description("Description")
                .build()
        );
    }

    @AfterEach
    void tearDown() {
        reviewRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void createReview() {
        // Given
        ReviewRequest reviewRequest = new ReviewRequest("New Review", 4.5f, testProduct.getId());

        // When
        reviewService.createReview(reviewRequest);
        List<ReviewResponse> reviewResponses = reviewService.getAllReviews();

        // Then
        assertNotNull(reviewResponses);
        assertNotNull(reviewResponses.get(1).getId());
        assertEquals("New Review", reviewResponses.get(1).getContent());
    }

    @Test
    void getReviewById() {
        // When
        ReviewResponse reviewResponse = reviewService.getReviewById(testReview.getId());

        // Then
        assertNotNull(reviewResponse);
        assertEquals(testReview.getId(), reviewResponse.getId());
    }

    @Test
    void getAllReviews() {
        // When
        List<ReviewResponse> reviewResponses = reviewService.getAllReviews();

        // Then
        assertNotNull(reviewResponses);
        assertEquals(1, reviewResponses.size());
    }

    @Test
    void deleteReview() {
        // When
        reviewService.deleteReview(testReview.getId());

        // Then
        assertTrue(reviewRepository.findById(testReview.getId()).orElseThrow().isDeleted());
    }
}