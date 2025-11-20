package com.beyond.beyond.mappers;

import com.beyond.beyond.dto.UserDTO;
import com.beyond.beyond.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.id());
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPasswordHash(dto.password_hash());

        return user;
    }

    public UserDTO toDto(User entity) {
        UserDTO userDTO = new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPasswordHash()
        );

        return userDTO;
    }
}
