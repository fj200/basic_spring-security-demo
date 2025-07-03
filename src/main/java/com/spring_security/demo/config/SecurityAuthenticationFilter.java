package com.spring_security.demo.config;

import com.spring_security.demo.common.AuthConstants;
import com.spring_security.demo.exceptions.TokenAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    private final AuthUserCache authUserCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader =request.getHeader(AuthConstants.AUTHORIZATION_HEADER);

        if (authenticationHeader == null){
            // Authentication token is not present, let's rely on anonymous authentication
            filterChain.doFilter(request, response);
            return;
        }

        AuthUser authUser = null;
        try {
            authUser = authUserCache
                    .getByToken(authenticationHeader)
                    .orElseThrow(() -> new TokenAuthenticationException("Token is not valid"));
        } catch (TokenAuthenticationException e) {
            throw new RuntimeException(e);
        }

        UserAuthentication authentication = new UserAuthentication(authUser);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        filterChain.doFilter(request, response);
    }
}
