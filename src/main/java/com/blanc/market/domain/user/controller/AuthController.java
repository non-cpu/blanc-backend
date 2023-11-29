package com.blanc.market.domain.user.controller;

import com.blanc.market.domain.user.dto.LoginRequest;
import com.blanc.market.domain.user.dto.LoginResponse;
import com.blanc.market.domain.user.dto.SignupRequest;
import com.blanc.market.domain.user.dto.UserDto;
import com.blanc.market.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth API Document")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 가입 메서드입니.")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequest signupRequest){
        UserDto user = authService.signup(signupRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 메서드입니다.")
    public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
}
