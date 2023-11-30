package com.blanc.market.domain.product.service;

import com.blanc.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NamedLockProductFacade {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Transactional
    public void updateLikeCount(Long productId, int incrementValue) {
        try {
            productRepository.getLock(productId.toString());
            productService.updateLikeCount(productId, incrementValue);
        } finally {
            productRepository.releaseLock(productId.toString());
        }
    }
}
