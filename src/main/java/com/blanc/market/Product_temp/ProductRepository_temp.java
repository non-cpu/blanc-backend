package com.blanc.market.Product_temp;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository_temp {

    private final EntityManager em;

    public Product_temp findOne(Long id){
        return em.find(Product_temp.class, id);
    }
}
