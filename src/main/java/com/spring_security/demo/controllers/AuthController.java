package com.spring_security.demo.controllers;

import com.spring_security.demo.common.AuthConstants;
import com.spring_security.demo.dto.LoginDTO;
import com.spring_security.demo.dto.TokenDTO;
import com.spring_security.demo.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO){
        return authService.login(loginDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest){
        String token = Optional
                .ofNullable(httpServletRequest
                        .getHeader(AuthConstants.AUTHORIZATION_HEADER))
                .orElseThrow();
        authService.logout(token);
    }
}
