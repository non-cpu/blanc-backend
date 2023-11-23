package com.blanc.market.domain.product.controller;

import com.blanc.market.domain.product.dto.request.ProductRequest;
import com.blanc.market.domain.product.dto.response.ProductResponse;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getAllProducts(page, size);

        return products.getContent().stream()
                .map(product -> {
                    ProductResponse dto = new ProductResponse();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setPrice(product.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }



    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }


    //검색 컨트롤러
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductResponse>> searchProduct(@PathVariable String keyword, Pageable pageable){
        return ResponseEntity.ok(productService.searchPage(keyword, pageable).getContent());
    }
}

