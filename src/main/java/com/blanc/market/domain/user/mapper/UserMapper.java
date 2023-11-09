package com.blanc.market.domain.user.mapper;

import com.blanc.market.domain.user.dto.LoginResponse;
import com.blanc.market.domain.user.dto.SignupRequest;
import com.blanc.market.domain.user.dto.UserDto;
import com.blanc.market.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(SignupRequest request);

    UserDto toUserDto(User user);

    LoginResponse toLoginResponse(User user);
}
