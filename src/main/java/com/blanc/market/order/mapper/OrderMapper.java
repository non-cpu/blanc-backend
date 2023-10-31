package com.blanc.market.order.mapper;

import com.blanc.market.order.dto.OrderRequest;
import com.blanc.market.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    //Order toEntity(OrderRequest dto);

}
