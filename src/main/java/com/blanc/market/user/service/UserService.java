package com.blanc.market.user.service;

import com.blanc.market.user.dto.SignupRequest;
import com.blanc.market.user.dto.UserDto;
import com.blanc.market.user.entity.Role;
import com.blanc.market.user.entity.User;
import com.blanc.market.user.mapper.UserMapper;
import com.blanc.market.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(Long userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(NoSuchElementException::new);
        return UserMapper.INSTANCE.toUserDto(user);
    }

    public UserDto updateUser(Long userId, SignupRequest updateRequest) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(NoSuchElementException::new);

        user.changeName(updateRequest.getName());
        user.changeEmail(updateRequest.getEmail());
        user.changePassword(updateRequest.getPassword());
        user.changeAddress(updateRequest.getAddress());
        user.changeRole(Role.USER);

        userRepository.save(user);

        return UserMapper.INSTANCE.toUserDto(user);
    }

    public boolean deleteUser(Long userId){
        User user = userRepository.findUserById(userId)
                .orElseThrow(NoSuchElementException::new);

        userRepository.delete(user);

        return true;
    }
}
