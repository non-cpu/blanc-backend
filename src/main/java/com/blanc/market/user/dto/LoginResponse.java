package com.blanc.market.user.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
public class LoginResponse {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String token;

    public void changeToken(String token) {
        this.token = token;
    }
}