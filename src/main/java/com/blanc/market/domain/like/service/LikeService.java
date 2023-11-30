package com.blanc.market.domain.like.service;

import com.blanc.market.domain.like.dto.LikeRequest;
import com.blanc.market.domain.like.dto.LikeResponse;
import com.blanc.market.domain.like.entity.Like;
import com.blanc.market.domain.like.mapper.LikeMapper;
import com.blanc.market.domain.like.repository.LikeRepository;
import com.blanc.market.domain.product.service.NamedLockProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final LikeMapper likeMapper;
    private final LikeRepository likeRepository;
    private final NamedLockProductFacade namedLockProductFacade;

    @Transactional
    public boolean toggleLike(LikeRequest request) {
        Optional<Like> existingLike = likeRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId());

        if (existingLike.isPresent()) {
            Like like = existingLike.get();
            namedLockProductFacade.updateLikeCount(like.getProduct().getId(), -1);
            likeRepository.deleteById(like.getId());
            return false;
        } else {
            namedLockProductFacade.updateLikeCount(request.getProductId(), 1);
            likeRepository.save(likeMapper.toEntity(request));
            return true;
        }
    }

    public List<LikeResponse> getAllLikesByUserId(Long userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes.stream().map(likeMapper::from).collect(Collectors.toList());
    }

    public List<LikeResponse> getAllLikes() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(likeMapper::from).collect(Collectors.toList());
    }

    public boolean isLikedByUserAndProduct(Long userId, Long productId) {
        return likeRepository.existsByUserIdAndProductId(userId, productId);
    }
}
