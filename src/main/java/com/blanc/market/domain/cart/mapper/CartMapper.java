package com.blanc.market.domain.cart.mapper;

import com.blanc.market.domain.cart.dto.CartRequest;
import com.blanc.market.domain.cart.dto.CartResponse;
import com.blanc.market.domain.cart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    Cart toEntity(CartRequest cartRequest);

    @Mapping(source = "product.id", target = "productId")
    CartResponse from(Cart cart);
}
