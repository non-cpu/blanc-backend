package com.blanc.market.domain.like.controller;

import com.blanc.market.domain.like.dto.LikeRequest;
import com.blanc.market.domain.like.dto.LikeResponse;
import com.blanc.market.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> createLike(@RequestBody LikeRequest likeRequest) {
        likeService.createLike(likeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikeResponse> getLikeById(@PathVariable Long id) {
        LikeResponse like = likeService.getLikeById(id);
        return like != null
                ? ResponseEntity.ok(like)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAllLikes() {
        return ResponseEntity.ok(likeService.getAllLikes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }
}
