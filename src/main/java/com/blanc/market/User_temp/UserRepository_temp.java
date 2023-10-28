package com.blanc.market.User_temp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository_temp {

    private EntityManager em;

    private void save(User_temp user){
        em.persist(user);
    }

    public User_temp findOne(Long id){
        return em.find(User_temp.class, id);
    }
}
