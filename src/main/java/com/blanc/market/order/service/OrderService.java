package com.blanc.market.order.service;

import com.blanc.market.Product_temp.ProductRepository_temp;
import com.blanc.market.Product_temp.Product_temp;
import com.blanc.market.User_temp.UserRepository_temp;
import com.blanc.market.User_temp.User_temp;
import com.blanc.market.order.entity.Order;
import com.blanc.market.order.entity.OrderProduct;
import com.blanc.market.order.dto.OrderRequest;
import com.blanc.market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository_temp userRepository;
    private final ProductRepository_temp productRepository;

    //주문
    public Long order(OrderRequest dto){

        //주문한 사용자 엔티티 조회
        User_temp user = userRepository.findOne(dto.getUserId());
        Product_temp product = productRepository.findOne(dto.getItemId());

        //주문 상품 생성
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, dto.getOrderprice(), dto.getCount());
        //주문 생성
        Order order = Order.createOrder(user, orderProduct);
        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    public void cancleOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
