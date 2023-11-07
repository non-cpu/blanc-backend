package com.blanc.market.user.controller;

import com.blanc.market.user.dto.LoginRequest;
import com.blanc.market.user.dto.LoginResponse;
import com.blanc.market.user.dto.SignupRequest;
import com.blanc.market.user.dto.UserDto;
import com.blanc.market.user.entity.User;
import com.blanc.market.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequest signupRequest){
        UserDto user = authService.signup(signupRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @GetMapping("/exception")
    public void sendException() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }
}
