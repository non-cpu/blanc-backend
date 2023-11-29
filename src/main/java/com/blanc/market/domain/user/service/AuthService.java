package com.blanc.market.domain.user.service;

import com.blanc.market.global.config.security.JwtProvider;
import com.blanc.market.domain.user.dto.LoginRequest;
import com.blanc.market.domain.user.dto.LoginResponse;
import com.blanc.market.domain.user.dto.SignupRequest;
import com.blanc.market.domain.user.dto.UserDto;
import com.blanc.market.domain.user.entity.CustomUserDetails;
import com.blanc.market.domain.user.entity.Role;
import com.blanc.market.domain.user.entity.User;
import com.blanc.market.domain.user.mapper.UserMapper;
import com.blanc.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Transactional
    public UserDto signup(SignupRequest signupRequest){
        User user = userMapper.toUser(signupRequest);
        user.changePassword(passwordEncoder.encode(user.getPassword()));
        user.changeRole(Role.USER);

        userRepository.save(user);

        UserDto userDto = userMapper.toUserDto(user);

        return userDto;
    }

    public LoginResponse login(LoginRequest request) {
//        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new BadCredentialsException("잘못된 계정정보입니다.");
//        }
//
//        LoginResponse response = UserMapper.INSTANCE.toLoginResponse(user);
//        response.changeToken();
//
//        return response;

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtProvider.generateToken(new CustomUserDetails(user));
        LoginResponse response = userMapper.toLoginResponse(user);
        response.changeToken(jwt);
        return response;
    }
}
