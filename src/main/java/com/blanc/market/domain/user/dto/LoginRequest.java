package com.blanc.market.domain.user.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;
}
