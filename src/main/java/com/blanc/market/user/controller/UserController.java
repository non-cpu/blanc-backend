package com.blanc.market.user.controller;

import com.blanc.market.user.dto.SignupRequest;
import com.blanc.market.user.dto.UserDto;
import com.blanc.market.user.entity.User;
import com.blanc.market.user.service.UserService;
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

    @PostMapping("/{user_id}")
    public UserDto updateUser(@PathVariable("user_id") Long userId,
                              @RequestBody SignupRequest updateRequest){
        return userService.updateUser(userId, updateRequest);
    }

    @DeleteMapping("/{user_id}")
    public boolean deleteUser(@PathVariable("user_id") Long userId){
        return userService.deleteUser(userId);
    }

}
