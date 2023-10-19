package com.blanc.market.domain.user.entity;

import com.blanc.market.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String username;

//    public void setUserRole(ROLE role) {
//        this.role = role;
//    }
}
