package com.blanc.market.domain.product.mapper_t;

import com.blanc.market.domain.product.dto.request.ProductRequest;
import com.blanc.market.domain.product.dto.response.ProductResponse;
import com.blanc.market.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toDto(Product entity);
}
