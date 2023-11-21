package com.blanc.market.domain.like.service;

import com.blanc.market.domain.like.dto.LikeRequest;
import com.blanc.market.domain.like.dto.LikeResponse;
import com.blanc.market.domain.like.entity.Like;
import com.blanc.market.domain.like.repository.LikeRepository;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.user.entity.User;
import com.blanc.market.domain.user.repository.UserRepository;
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
class LikeServiceTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private Like testLike;
    private User testUser;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testLike = likeRepository.save(Like.builder().build());

        testUser = userRepository.save(User.builder().build());

        testProduct = productRepository.save(Product.builder()
                .name("Test Product")
                .description("Description")
                .build()
        );
    }

    @AfterEach
    void tearDown() {
        likeRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void getAllLikes() {
        // When
        List<LikeResponse> likeResponses = likeService.getAllLikes();

        // Then
        assertNotNull(likeResponses);
        assertEquals(1, likeResponses.size());
    }

    @Test
    void getLikeById() {
        // When
        LikeResponse likeResponse = likeService.getLikeById(testLike.getId());

        // Then
        assertNotNull(likeResponse);
        assertEquals(testLike.getId(), likeResponse.getId());
    }

    @Test
    void createLike() {
        // Given
        LikeRequest likeRequest = new LikeRequest(testUser.getId(), testProduct.getId());

        // When
        likeService.createLike(likeRequest);
        List<LikeResponse> likeResponses = likeService.getAllLikes();

        // Then
        assertNotNull(likeResponses);
        assertNotNull(likeResponses.get(1).getId());
        assertEquals(testProduct.getId(), likeResponses.get(1).getProductId());
    }

    @Test
    void deleteLike() {
        // When
        likeService.deleteLike(testLike.getId());

        // Then
        assertNull(likeRepository.findById(testLike.getId()).orElse(null));
    }
}