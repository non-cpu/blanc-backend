package com.blanc.market.user.service;

import com.blanc.market.config.security.JwtProvider;
import com.blanc.market.user.dto.LoginRequest;
import com.blanc.market.user.dto.LoginResponse;
import com.blanc.market.user.dto.SignupRequest;
import com.blanc.market.user.dto.UserDto;
import com.blanc.market.user.entity.CustomUserDetails;
import com.blanc.market.user.entity.Role;
import com.blanc.market.user.entity.User;
import com.blanc.market.user.mapper.UserMapper;
import com.blanc.market.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public UserDto signup(SignupRequest signupRequest){
        User user = UserMapper.INSTANCE.toUser(signupRequest);
        user.changePassword(passwordEncoder.encode(user.getPassword()));
        user.changeRole(Role.USER);

        userRepository.save(user);

        UserDto userDto = UserMapper.INSTANCE.toUserDto(user);

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
        LoginResponse response = UserMapper.INSTANCE.toLoginResponse(user);
        response.changeToken(jwt);
        return response;
    }
}
