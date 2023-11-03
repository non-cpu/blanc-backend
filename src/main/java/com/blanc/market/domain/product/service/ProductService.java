package com.blanc.market.domain.product.service;

import com.blanc.market.domain.product.dto.request.ProductRequest;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.review.dto.ReviewDto;
import com.blanc.market.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).price(productRequest.getPrice()).description(productRequest.getDescription()).build();
        productRepository.save(product);
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Transactional
    public void addReviewToProduct(Long productId, ReviewDto reviewDTO) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            Review reviewEntity = new Review();
            reviewEntity.setContent(reviewDTO.getContent());
            reviewEntity.setRating(reviewDTO.getRating());

            // not done

            reviewEntity.setProduct(product);

            product.getReviews().add(reviewEntity);
            productRepository.save(product);
        }
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.delete();
    }
}
