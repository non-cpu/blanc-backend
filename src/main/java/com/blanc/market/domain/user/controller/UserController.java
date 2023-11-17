package com.blanc.market.domain.user.controller;

import com.blanc.market.domain.user.dto.SignupRequest;
import com.blanc.market.domain.user.dto.UserDto;
import com.blanc.market.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{user_id}")
    public UserDto getUser(@PathVariable("user_id") Long userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/{user_id}")
    public boolean deleteUser(@PathVariable("user_id") Long userId){
        return userService.deleteUser(userId);
    }

}
