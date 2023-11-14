package com.blanc.market.domain.order.mapper;

import com.blanc.market.domain.order.dto.OrderRequest;
import com.blanc.market.domain.order.dto.OrderResponse;
import com.blanc.market.domain.order.entity.Order;
import com.blanc.market.domain.order.repository.OrderRepository;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.service.ProductService;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequest dto);

    OrderResponse toDto(Order entity);



}
