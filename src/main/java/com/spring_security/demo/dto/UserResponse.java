package com.spring_security.demo.dto;

import com.spring_security.demo.common.Role;

import java.util.List;

public record UserResponse(String id, String userName, String firstName, String lastName, List<Role> roles, Boolean active) {
}
