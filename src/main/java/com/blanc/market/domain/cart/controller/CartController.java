package com.blanc.market.domain.cart.controller;

import com.blanc.market.domain.cart.dto.request.CartRequest;
import com.blanc.market.domain.cart.dto.response.CartResponse;
import com.blanc.market.domain.cart.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Void> addItemToCart(@RequestBody CartRequest cartRequest) {
        cartService.addItemToCart(cartRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCartByUserId(@PathVariable Long userId) {
        List<CartResponse> cartItems = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable Long cartId, @RequestParam int quantity) {
        cartService.updateCartItemQuantity(cartId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId) {
        cartService.removeItemFromCart(cartId);
        return ResponseEntity.ok().build();
    }
}
