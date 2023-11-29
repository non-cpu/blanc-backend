package com.blanc.market.domain.product.mapper;

import com.blanc.market.domain.ingredient.mapper.IngredientMapper;
import com.blanc.market.domain.product.dto.ProductRequest;
import com.blanc.market.domain.product.dto.ProductResponse;
import com.blanc.market.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "likeCount", ignore = true)
    Product toEntity(ProductRequest productRequest);

    @Mapping(source = "productIngredients", target = "ingredients")
    ProductResponse from(Product product);
}
