package com.example.spartaschedule.service;


import com.example.spartaschedule.dto.UserRequestDto;
import com.example.spartaschedule.dto.UserResponseDto;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto dto);
}
