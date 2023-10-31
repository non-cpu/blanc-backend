package com.blanc.market.order.entity;

import com.blanc.market.Product_temp.Product_temp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderProduct {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product_temp product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderprice;

    private int count;

    //생성 메서드
    public static OrderProduct createOrderProduct(Product_temp product, int orderprice, int count){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderprice(orderprice);
        orderProduct.setCount(count);

        return orderProduct;
    }

}
