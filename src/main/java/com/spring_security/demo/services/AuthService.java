package com.spring_security.demo.services;

import com.spring_security.demo.config.AuthUser;
import com.spring_security.demo.config.AuthUserCache;
import com.spring_security.demo.dto.LoginDTO;
import com.spring_security.demo.dto.TokenDTO;
import com.spring_security.demo.dto.UserResponse;
import com.spring_security.demo.dto.UserResponseWithCredentials;
import com.spring_security.demo.exceptions.ApplicationAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthUserCache authUserCache;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO login(LoginDTO loginDTO) throws ApplicationAuthenticationException {
        UserResponseWithCredentials userResponseWithCredentials = userService
                .getUserCredentialsByUserName(loginDTO.userName());

        if(!passwordEncoder.matches(loginDTO.password(), userResponseWithCredentials.passwordHash())){
            throw new ApplicationAuthenticationException("Password is incorrect");
        }

        String token = UUID.randomUUID().toString();
        UserResponse userResponse = userResponseWithCredentials.userResponse();
        AuthUser authUser = new AuthUser((userResponse.id()), userResponse.roles());
        authUserCache.login(token, authUser);
        return new TokenDTO(token);
    }

    public void logout(String token){
        authUserCache.logout(token);
    }

}
