package com.blanc.market.order.entity;

import com.blanc.market.Product_temp.ProductTemp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    private ProductTemp product;

    @ManyToOne
    private Order order;

    private int orderprice;

    private int count;

}
