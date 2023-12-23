package com.blanc.market.domain.user.dto;

import com.blanc.market.domain.user.entity.SkinConcerns;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String address;

    private String gender;

    private String skinType;

    private List<SkinConcerns> skinConcerns = new ArrayList<>();
}
