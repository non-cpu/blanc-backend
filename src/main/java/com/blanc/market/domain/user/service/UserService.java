package com.blanc.market.domain.user.service;

import com.blanc.market.domain.user.dto.SignupRequest;
import com.blanc.market.domain.user.dto.UserDto;
import com.blanc.market.domain.user.entity.Role;
import com.blanc.market.domain.user.entity.User;
import com.blanc.market.domain.user.mapper.UserMapper;
import com.blanc.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUser(Long userId) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(NoSuchElementException::new);
        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDto updateUser(Long userId, SignupRequest updateRequest) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(NoSuchElementException::new);

        user.changeNickname(updateRequest.getNickname());
        user.changeEmail(updateRequest.getEmail());
        user.changePassword(updateRequest.getPassword());
        user.changeAddress(updateRequest.getAddress());
        user.changeSkinConcerns(updateRequest.getSkinConcerns());
        user.changeSkinType(updateRequest.getSkinType());
        user.changeRole(Role.USER);

        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    @Transactional
    public boolean deleteUser(Long userId){
        User user = userRepository.findUserById(userId)
                .orElseThrow(NoSuchElementException::new);

        user.delete();

        return true;
    }
}
