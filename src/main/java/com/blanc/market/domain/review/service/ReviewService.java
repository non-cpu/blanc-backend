package com.blanc.market.domain.review.service;

import com.blanc.market.domain.review.dto.ReviewRequest;
import com.blanc.market.domain.review.dto.ReviewResponse;
import com.blanc.market.domain.review.entity.Review;
import com.blanc.market.domain.review.mapper.ReviewMapper;
import com.blanc.market.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview(ReviewRequest request) {
        reviewRepository.save(reviewMapper.toEntity(request));
    }

    public ReviewResponse getReviewById(Long id) {
        return reviewMapper.from(reviewRepository.findById(id).orElse(null));
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(reviewMapper::from).collect(Collectors.toList());
    }

    @Transactional
    public void updateReview(Long id, ReviewRequest request) {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.update(request);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.delete();
    }
}
