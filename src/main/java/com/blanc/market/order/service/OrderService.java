package com.blanc.market.order.service;

import com.blanc.market.Product_temp.ProductRepository_temp;
import com.blanc.market.Product_temp.Product_temp;
import com.blanc.market.User_temp.UserRepository_temp;
import com.blanc.market.User_temp.User_temp;
import com.blanc.market.order.dto.OrderResponse;
import com.blanc.market.order.entity.Order;
import com.blanc.market.order.entity.OrderProduct;
import com.blanc.market.order.dto.OrderRequest;
import com.blanc.market.order.entity.OrderStatus;
import com.blanc.market.order.mapper.OrderMapper;
import com.blanc.market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository_temp userRepository;
    private final ProductRepository_temp productRepository;

    private final OrderMapper orderMapper;

    //주문
    @Transactional
    public OrderResponse order(OrderRequest dto){

        //주문한 사용자 엔티티 조회
        User_temp user = userRepository.findOne(dto.getUserId());
        Product_temp product = productRepository.findOne(dto.getProductId());

        //주문상품 생성
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, dto.getOrderprice(), dto.getCount());
        //주문 생성
        Order order = Order.createOrder(user, orderProduct);
        order.setStatus(OrderStatus.ORDER);
        //주문 저장
        orderRepository.save(order);

        return orderMapper.toDto(order);

    }

    @Transactional
    public void cancleOrders(Long id){
        getEntity(id).cancelOrder();
    }


    public Order getEntity(Long id){
        return orderRepository.findOne(id);  //예외처리 추가햐야함
    }
}
