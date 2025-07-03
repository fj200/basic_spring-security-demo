package com.spring_security.demo.dto;

public record UserPasswordUpdateRequest(String oldPassword, String newPassword) {}
