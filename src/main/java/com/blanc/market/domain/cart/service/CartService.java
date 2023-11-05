package com.blanc.market.domain.cart.service;

import com.blanc.market.domain.cart.dto.request.CartRequest;
import com.blanc.market.domain.cart.dto.response.CartResponse;
import com.blanc.market.domain.cart.entity.Cart;
import com.blanc.market.domain.cart.repository.CartRepository;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.user.entity.User;
import com.blanc.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addItemToCart(CartRequest cartRequest) {
        User user = userRepository.findById(cartRequest.getUserId()).orElseThrow();
        Product product = productRepository.findById(cartRequest.getProductId()).orElseThrow();

        cartRepository.save(Cart.builder()
                .user(user)
                .product(product)
                .productQuantity(cartRequest.getProductQuantity())
                .build());
    }

    public List<CartResponse> getCartByUserId(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId).orElseThrow();
        return cartItems.stream()
                .map(CartResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateCartItemQuantity(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.updateProductQuantity(quantity);
    }

    @Transactional
    public void removeItemFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
