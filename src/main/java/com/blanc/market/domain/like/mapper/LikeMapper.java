package com.blanc.market.domain.like.mapper;

import com.blanc.market.domain.like.dto.LikeRequest;
import com.blanc.market.domain.like.dto.LikeResponse;
import com.blanc.market.domain.like.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    Like toEntity(LikeRequest likeRequest);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    LikeResponse from(Like like);
}
