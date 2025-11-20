package com.beyond.beyond.dto;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String name,
    String email,
    String password_hash
) {
}
