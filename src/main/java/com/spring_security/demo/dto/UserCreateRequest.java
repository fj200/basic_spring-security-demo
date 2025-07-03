package com.spring_security.demo.dto;

public record UserCreateRequest(String username, String password, String firstName, String lastName) {
}
