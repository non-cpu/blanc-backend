package com.blanc.market.domain.order.repository;

import com.blanc.market.domain.order.entity.Order;
import com.blanc.market.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser(User currentUser);
}
