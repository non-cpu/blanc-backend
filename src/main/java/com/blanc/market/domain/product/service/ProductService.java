package com.blanc.market.domain.product.service;


import com.blanc.market.domain.product.dto.request.ProductRequest;
import com.blanc.market.domain.product.dto.response.ProductResponse;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.mapper_t.ProductMapper;
import com.blanc.market.domain.ingredient.dto.IngredientRequest;
import com.blanc.market.domain.ingredient.entity.Ingredient;
import com.blanc.market.domain.ingredient.entity.ProductIngredient;
import com.blanc.market.domain.ingredient.mapper.IngredientMapper;
import com.blanc.market.domain.ingredient.repository.IngredientRepository;
import com.blanc.market.domain.ingredient.repository.ProductIngredientRepository;
import com.blanc.market.domain.product.dto.ProductRequest;
import com.blanc.market.domain.product.dto.ProductResponse;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.mapper.ProductMapper;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.review.dto.ReviewResponse;
import com.blanc.market.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {
    private final ReviewMapper reviewMapper;
    private final ProductMapper productMapper;
    private final IngredientMapper ingredientMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;


    @Transactional
    public void createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);

        productRepository.save(product);

        Set<IngredientRequest> ingredients = request.getIngredients();

        if (ingredients != null) {
            for (IngredientRequest ingredientRequest : ingredients) {
                Ingredient ingredient = ingredientRepository.findByName(ingredientRequest.getName())
                        .orElseGet(() -> {
                                    Ingredient newIngredient = ingredientMapper.toEntity(ingredientRequest);
                                    ingredientRepository.save(newIngredient);
                                    return newIngredient;
                                }
                        );

                ProductIngredient productIngredient = ProductIngredient.builder()
                        .ingredient(ingredient)
                        .product(product)
                        .build();

                productIngredientRepository.save(productIngredient);

                product.addProductIngredient(productIngredient);
            }
        }
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return productMapper.from(product);
    }

    public List<ReviewResponse> getReviewsForProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();

        return product.getReviews() != null
                ? product.getReviews().stream().map(reviewMapper::from).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public Page<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::from);
    }

    @Transactional
    public void incrementLikeCount(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setLikeCount(product.getLikeCount() + 1);
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
