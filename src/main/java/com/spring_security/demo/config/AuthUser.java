package com.spring_security.demo.config;

import com.spring_security.demo.common.Role;

import java.util.List;

public record AuthUser(String userId, List<Role> roles) {
}
