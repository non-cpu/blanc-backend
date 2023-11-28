package com.blanc.market.domain.product.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ReviewMapper reviewMapper;
    private final ProductMapper productMapper;
    private final IngredientMapper ingredientMapper;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static";

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

    public Page<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::from);
    }

    @Transactional
    public void uploadFile(Long productId, MultipartFile file) {
        Product product = productRepository.findById(productId).orElseThrow();

        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일을 선택해주세요.");
        }

        if (!isValidFileType(file.getOriginalFilename())) {
            throw new IllegalArgumentException("지원하지 않는 파일 형식 입니다.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기가 허용 범위를 초과했습니다.");
        }

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new RuntimeException("디렉토리 생성에 실패했습니다.");
        }

        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

        try {
            File destFile = new File(uploadDir.getAbsolutePath() + File.separator + fileName);
            file.transferTo(destFile);

            product.setImageUrl(fileName);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.");
        }
    }

    private boolean isValidFileType(String fileName) {
        String[] allowedFileTypes = {"jpg", "jpeg", "png", "gif"};
        String fileExtension = StringUtils.getFilenameExtension(fileName);

        for (String allowedType : allowedFileTypes) {
            if (allowedType.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }

    public List<ReviewResponse> getReviewsForProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();

        return product.getReviews() != null
                ? product.getReviews().stream().map(reviewMapper::from).collect(Collectors.toList())
                : Collections.emptyList();
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
}
