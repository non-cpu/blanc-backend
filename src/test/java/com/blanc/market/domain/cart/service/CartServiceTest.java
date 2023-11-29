package com.blanc.market.domain.cart.service;

import com.blanc.market.domain.cart.dto.CartRequest;
import com.blanc.market.domain.cart.dto.CartResponse;
import com.blanc.market.domain.cart.entity.Cart;
import com.blanc.market.domain.cart.repository.CartRepository;
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
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    private User testUser;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testUser = userRepository.save(User.builder()
                .name("testUser")
                .email("test@example.com")
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
        cartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void addItemToCart() {
        // Given
        CartRequest cartRequest = new CartRequest(2, testUser.getId(), testProduct.getId());

        // When
        cartService.addItemToCart(cartRequest);

        // Then
        List<Cart> cartItems = cartRepository.findByUserId(testUser.getId()).orElseThrow();
        assertEquals(1, cartItems.size());

        Cart cartItem = cartItems.get(0);
        assertEquals(testUser.getId(), cartItem.getUser().getId());
        assertEquals(testProduct.getId(), cartItem.getProduct().getId());
        assertEquals(2, cartItem.getProductQuantity());
    }

    @Test
    void getCartByUserId() {
        // Given
        cartRepository.save(Cart.builder()
                .user(testUser)
                .product(testProduct)
                .productQuantity(3)
                .build()
        );

        // When
        List<CartResponse> cartResponses = cartService.getCartByUserId(testUser.getId());

        // Then
        assertNotNull(cartResponses);
        assertEquals(1, cartResponses.size());

        CartResponse cartResponse = cartResponses.get(0);
        assertEquals(testProduct.getId(), cartResponse.getProductId());
        assertEquals(3, cartResponse.getProductQuantity());
    }

    @Test
    void updateCartItemQuantity() {
        // Given
        Cart cartItem = Cart.builder()
                .user(testUser)
                .product(testProduct)
                .productQuantity(3)
                .build();

        cartRepository.save(cartItem);

        // When
        cartService.updateCartItemQuantity(cartItem.getId(), 5);

        // Then
        Cart updatedCartItem = cartRepository.findById(cartItem.getId()).orElseThrow();
        assertEquals(5, updatedCartItem.getProductQuantity());
    }

    @Test
    void removeItemFromCart() {
        // Given
        Cart cartItem = Cart.builder()
                .user(testUser)
                .product(testProduct)
                .productQuantity(3)
                .build();

        cartRepository.save(cartItem);

        // When
        cartService.removeItemFromCart(cartItem.getId());

        // Then
        List<Cart> cartItems = cartRepository.findByUserId(testUser.getId()).orElseThrow();
        assertEquals(0, cartItems.size());
    }
}