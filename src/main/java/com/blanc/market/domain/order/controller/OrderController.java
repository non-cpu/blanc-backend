package com.blanc.market.domain.order.controller;


import com.blanc.market.domain.order.dto.OrderRequest;
import com.blanc.market.domain.order.dto.OrderResponse;
import com.blanc.market.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/api/orders")
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody @Valid OrderRequest dto){
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.order(dto));
    }

    //주문 상태 취소로 변경
    @PostMapping("api/orders/{id}/cancle")
    public ResponseEntity<Void> cancleOrders(@PathVariable Long id){
        orderService.cancleOrders(id);
        return ResponseEntity.ok().build();
    }

    //완전 삭제도 필요?


    //사용자 주문 조회
    @GetMapping("api/orders/my-orders")
    public ResponseEntity<List<OrderResponse>> getMyOrders(){
        return ResponseEntity.ok(orderService.findAllMyOrders());
    }



}
