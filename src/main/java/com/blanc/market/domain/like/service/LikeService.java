package com.blanc.market.domain.like.service;

import com.blanc.market.domain.like.dto.LikeRequest;
import com.blanc.market.domain.like.dto.LikeResponse;
import com.blanc.market.domain.like.entity.Like;
import com.blanc.market.domain.like.mapper.LikeMapper;
import com.blanc.market.domain.like.repository.LikeRepository;
import com.blanc.market.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final LikeMapper likeMapper;
    private final LikeRepository likeRepository;
    private final ProductService productService;

    @Transactional
    public void createLike(LikeRequest likeRequest) {
        productService.incrementLikeCount(likeRequest.getProductId());
        likeRepository.save(likeMapper.toEntity(likeRequest));
    }

    public LikeResponse getLikeById(Long id) {
        return likeMapper.from(likeRepository.findById(id).orElse(null));
    }

    public List<LikeResponse> getAllLikes() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(likeMapper::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}
