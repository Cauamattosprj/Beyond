package com.beyond.beyond.controllers;

import com.beyond.beyond.dto.UserDTO;
import com.beyond.beyond.mappers.UserMapper;
import com.beyond.beyond.models.User;
import com.beyond.beyond.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(savedUser));
    }
}
