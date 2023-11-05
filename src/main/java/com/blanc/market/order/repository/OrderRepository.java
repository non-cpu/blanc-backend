package com.blanc.market.order.repository;

import com.blanc.market.User_temp.User_temp;
import com.blanc.market.order.entity.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser(User_temp currentUser);
}
