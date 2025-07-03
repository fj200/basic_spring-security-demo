package com.spring_security.demo.exceptions;

import javax.naming.AuthenticationException;

public class ApplicationAuthenticationException extends AuthenticationException {
    public ApplicationAuthenticationException(String message) {
        super(message);
    }
}
