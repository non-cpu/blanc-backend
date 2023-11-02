package com.blanc.market.order.controller;


import com.blanc.market.order.dto.OrderRequest;
import com.blanc.market.order.dto.OrderResponse;
import com.blanc.market.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //ResponseEntity 쓰면 깔끔?
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


}
