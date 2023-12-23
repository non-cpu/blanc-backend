package com.blanc.market.domain.user.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private Long id;

    private String nickname;

    private String email;

    private String token;

    public void changeToken(String token) {
        this.token = token;
    }
}