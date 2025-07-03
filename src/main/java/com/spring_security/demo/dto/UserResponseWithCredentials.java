package com.spring_security.demo.dto;

public record UserResponseWithCredentials(UserResponse userResponse, String passwordHash) {
}
