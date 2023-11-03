package com.blanc.market.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String name;

    private String email;

    private String password;

    private String address;
}
