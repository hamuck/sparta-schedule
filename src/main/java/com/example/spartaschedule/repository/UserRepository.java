package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.UserResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.entity.User;

public interface UserRepository {
    Number saveUserToSchedule(Schedule schedule);
    UserResponseDto saveUser(User user);
}
