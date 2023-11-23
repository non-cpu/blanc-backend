package com.blanc.market.domain.user.controller;

import com.blanc.market.domain.user.dto.SignupRequest;
import com.blanc.market.domain.user.dto.UserDto;
import com.blanc.market.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User API Document")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{user_id}")
    @Operation(summary = "회원 조회", description = "userId를 통한 회원 조회 메서드입니다.")
    public UserDto getUser(@PathVariable("user_id") Long userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/{user_id}")
    public boolean deleteUser(@PathVariable("user_id") Long userId){
        return userService.deleteUser(userId);
    }

}
