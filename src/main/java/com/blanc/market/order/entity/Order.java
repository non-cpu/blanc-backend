package com.blanc.market.order.entity;

import com.blanc.market.User_temp.User_temp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User_temp user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    //private int baseTime;


    // 양방향 연관관계
    public void setUser(User_temp user){
        this.user = user;
        user.getOrder().add(this);
    }
    public void addOrederProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    //생성 메서드
    public static Order createOrder(User_temp user,OrderProduct... orderProducts ){
        Order order = new Order();
        order.setUser(user);
        for (OrderProduct orderProduct : orderProducts){
            order.addOrederProduct(orderProduct);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


    //비지니스로직
    public void cancelOrder(){
        this.setStatus(OrderStatus.CANCEL);
    }
}
