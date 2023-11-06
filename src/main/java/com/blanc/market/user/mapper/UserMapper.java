package com.blanc.market.user.mapper;

import com.blanc.market.user.dto.LoginResponse;
import com.blanc.market.user.dto.SignupRequest;
import com.blanc.market.user.dto.UserDto;
import com.blanc.market.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(SignupRequest request);

    UserDto toUserDto(User user);

    LoginResponse toLoginResponse(User user);
}