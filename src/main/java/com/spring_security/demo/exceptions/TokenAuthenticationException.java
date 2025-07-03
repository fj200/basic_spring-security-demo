package com.spring_security.demo.exceptions;

import javax.naming.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException {
    public TokenAuthenticationException(String message) {
        super(message);
    }
}
