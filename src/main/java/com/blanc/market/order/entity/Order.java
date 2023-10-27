package com.blanc.market.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    private Long id;

    private User user;

    @OneToMany
    private List<OrderItem> orderItem = new ArrayList<>();

    private OrderStatus status;


    //비지니스로직
    public void cancel(){

    }
}
