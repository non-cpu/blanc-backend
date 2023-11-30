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
import org.springframework.transaction.annotation.Propagation;
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

    private User testUser;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        likeRepository.save(Like.builder().build());
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
    @Transactional(propagation = Propagation.NEVER)
    void toggleLike() {
        // Given
        LikeRequest likeRequest = new LikeRequest(testUser.getId(), testProduct.getId());

        // When
        assertTrue(likeService.toggleLike(likeRequest));
        List<LikeResponse> likeResponses = likeService.getAllLikes();

        // Then
        assertNotNull(likeResponses);
        assertNotNull(likeResponses.get(1).getId());
        assertEquals(testProduct.getId(), likeResponses.get(1).getProductId());

        assertFalse(likeService.toggleLike(likeRequest));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void getAllLikesByUserId() {
        // Given
        LikeRequest likeRequest = new LikeRequest(testUser.getId(), testProduct.getId());
        likeService.toggleLike(likeRequest);

        // When
        List<LikeResponse> likeResponses = likeService.getAllLikesByUserId(testUser.getId());

        // Then
        assertNotNull(likeResponses);
        assertEquals(1, likeResponses.size());
        assertEquals(testUser.getId(), likeResponses.get(0).getUserId());
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
    @Transactional(propagation = Propagation.NEVER)
    void isLikedByUserAndProduct() {
        // Given
        LikeRequest likeRequest = new LikeRequest(testUser.getId(), testProduct.getId());
        likeService.toggleLike(likeRequest);

        // When
        boolean isLiked = likeService.isLikedByUserAndProduct(testUser.getId(), testProduct.getId());

        // Then
        assertTrue(isLiked);
    }
}