package com.blanc.market.domain.like.controller;

import com.blanc.market.domain.like.dto.LikeRequest;
import com.blanc.market.domain.like.dto.LikeResponse;
import com.blanc.market.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<Boolean> toggleLike(@RequestBody LikeRequest request) {
        return ResponseEntity.ok(likeService.toggleLike(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LikeResponse>> getAllLikesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(likeService.getAllLikesByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAllLikes() {
        return ResponseEntity.ok(likeService.getAllLikes());
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkLike(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(likeService.isLikedByUserAndProduct(userId, productId));
    }
}
