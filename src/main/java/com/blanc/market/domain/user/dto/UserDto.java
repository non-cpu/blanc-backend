package com.blanc.market.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;
}
