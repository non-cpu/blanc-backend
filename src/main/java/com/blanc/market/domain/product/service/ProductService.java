package com.blanc.market.domain.product.service;

import com.blanc.market.domain.ingredient.dto.IngredientRequest;
import com.blanc.market.domain.ingredient.entity.Ingredient;
import com.blanc.market.domain.ingredient.entity.ProductIngredient;
import com.blanc.market.domain.ingredient.mapper.IngredientMapper;
import com.blanc.market.domain.ingredient.repository.IngredientRepository;
import com.blanc.market.domain.ingredient.repository.ProductIngredientRepository;
import com.blanc.market.domain.product.dto.request.ProductRequest;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;

    @Transactional
    public void createProduct(ProductRequest productRequest) {
        // 상품 생성
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .productIngredients(new HashSet<>())
                .build();

        Set<IngredientRequest> ingredients = productRequest.getIngredients();
        // 기존 디비에 있는 성분인지 아닌지 판단
        for (IngredientRequest ingredient : ingredients) {
            boolean existIngredient =
                    ingredientRepository.existsByName(ingredient.getName());

            Ingredient newIngredient = IngredientMapper.INSTANCE.toEntity(ingredient);
            if(!existIngredient) { // 디비에 없을 시에만 저장
                ingredientRepository.save(newIngredient);
            }

            // 중간 테이블인 ProductIngredient 생성
            ProductIngredient productIngredient = ProductIngredient.builder()
                                                    .ingredient(newIngredient)
                                                    .product(product)
                                                    .build();
            productIngredientRepository.save(productIngredient);

            product.addProductIngredient(productIngredient);
//            productRepository.save(product);
        }


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
}
