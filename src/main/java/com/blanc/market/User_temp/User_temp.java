package com.blanc.market.User_temp;

import com.blanc.market.order.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User_temp {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private List<Order> order = new ArrayList<>();


}
