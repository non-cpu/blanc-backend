package com.blanc.market.domain.product.service;

import com.blanc.market.domain.product.dto.request.ProductRequest;
import com.blanc.market.domain.product.dto.response.ProductResponse;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.mapper_t.ProductMapper;
import com.blanc.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).price(productRequest.getPrice()).description(productRequest.getDescription()).build();
        productRepository.save(product);
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

//    @Transactional
//    public void addReviewToProduct(Long productId, ReviewDto reviewDTO) {
//        Product product = productRepository.findById(productId).orElse(null);
//        if (product != null) {
//            Review reviewEntity = new Review();
//            reviewEntity.setContent(reviewDTO.getContent());
//            reviewEntity.setRating(reviewDTO.getRating());
//
//            // not done
//
//            reviewEntity.setProduct(product);
//
//            product.getReviews().add(reviewEntity);
//            productRepository.save(product);
//        }
//    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.delete();
    }


    //제품검색
    @Transactional
    public List<ProductResponse> search(String keyword){
        return productRepository.findByNameContaining(keyword).stream()
                .map(productMapper::toDto).toList();
    }

    @Transactional
    public Page<ProductResponse> searchPage(String keyword, Pageable pageable){
        return productRepository.findByNameContainingOrderByName(keyword, pageable)
                .map(productMapper::toDto);
    }
}
