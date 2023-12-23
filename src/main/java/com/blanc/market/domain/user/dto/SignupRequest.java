package com.blanc.market.domain.user.dto;

import com.blanc.market.domain.user.entity.Gender;
import com.blanc.market.domain.user.entity.SkinConcerns;
import com.blanc.market.domain.user.entity.SkinType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String nickname;

    private String email;

    private String password;

    private String address;

    private Gender gender;

    private SkinType skinType;

    private List<SkinConcerns> skinConcerns = new ArrayList<>();
}
