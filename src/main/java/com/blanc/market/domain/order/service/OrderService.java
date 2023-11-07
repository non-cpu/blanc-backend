package com.blanc.market.domain.order.service;

import com.blanc.market.domain.order.dto.OrderRequest;
import com.blanc.market.domain.order.dto.OrderResponse;
import com.blanc.market.domain.order.entity.Order;
import com.blanc.market.domain.order.entity.OrderProduct;
import com.blanc.market.domain.order.entity.OrderStatus;
import com.blanc.market.domain.order.mapper.OrderMapper;
import com.blanc.market.domain.order.repository.OrderRepository;
import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.user.entity.User;
import com.blanc.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    //주문
    @Transactional
    public OrderResponse order(OrderRequest dto){

        //주문한 사용자 엔티티 조회
        User user = userRepository.findUserById(dto.getUserId()).orElseThrow(NoSuchElementException::new);
        Product product = productRepository.findById(dto.getProductId()).orElseThrow(NoSuchElementException::new);

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

    public List<OrderResponse> findAllMyOrders(){

        //User_temp loginUser = authService.getLoginUser();
        Long testId = Long.valueOf(1);

        User loginUser = userRepository.findUserById(testId).orElseThrow(NoSuchElementException::new);
        return orderRepository.findAllByUser(loginUser).stream()
                .map(orderMapper::toDto).toList();
    }


    public Order getEntity(Long id){
        return orderRepository.findById(id).get();  //예외처리 추가햐야함
    }
}
