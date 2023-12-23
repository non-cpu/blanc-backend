package com.blanc.market.domain.user.dto;

import com.blanc.market.domain.user.entity.Gender;
import com.blanc.market.domain.user.entity.SkinConcerns;
import com.blanc.market.domain.user.entity.SkinType;
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

    private Gender gender;

    private SkinType skinType;

    private List<SkinConcerns> skinConcerns = new ArrayList<>();
}
