package com.blanc.market.user.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String name;

    private String email;

    private String password;

    private String address;
}
