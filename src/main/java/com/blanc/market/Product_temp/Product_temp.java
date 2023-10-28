package com.blanc.market.Product_temp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
public class Product_temp {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;


}
