package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.UserRequestDto;
import com.example.spartaschedule.dto.UserResponseDto;
import com.example.spartaschedule.entity.User;
import com.example.spartaschedule.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto dto){
        User user = new User(dto.getName(), dto.getMaill(),dto.getCreateAt());
        if (user.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The username is not required values.");
        }
        return userRepository.saveUser(user);
    }
}
