package com.blanc.market.domain.review.mapper;

import com.blanc.market.domain.review.dto.ReviewRequest;
import com.blanc.market.domain.review.dto.ReviewResponse;
import com.blanc.market.domain.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "productId", target = "product.id")
    Review toEntity(ReviewRequest reviewRequest);

    @Mapping(source = "product.id", target = "productId")
    ReviewResponse from(Review review);
}
