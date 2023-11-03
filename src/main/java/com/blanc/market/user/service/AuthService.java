package com.blanc.market.user.service;

import com.blanc.market.user.dto.SignupRequest;
import com.blanc.market.user.dto.UserDto;
import com.blanc.market.user.entity.User;
import com.blanc.market.user.mapper.UserMapper;
import com.blanc.market.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public UserDto signup(SignupRequest signupRequest){
        User user = UserMapper.INSTANCE.toUser(signupRequest);
        userRepository.save(user);

        UserDto userDto = UserMapper.INSTANCE.toUserDto(user);

        return userDto;
    }
}
