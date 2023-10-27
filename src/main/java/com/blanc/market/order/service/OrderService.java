package com.blanc.market.order.service;

import com.blanc.market.order.entity.Order;
import com.blanc.market.order.entity.OrderItem;
import com.blanc.market.order.dto.OrderRequest;
import com.blanc.market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    //주문
    public Long order(OrderRequest dto){

        //주문한 사용자 엔티티 조회
        User user = userRepository.findOne(dto.getUserId());
        Item item = itemRepository.findOne(dto.getItemId());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrder();

        //주문 생성
        Order order = Order.createOrder(user, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId()

    }

    public void cancleOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
