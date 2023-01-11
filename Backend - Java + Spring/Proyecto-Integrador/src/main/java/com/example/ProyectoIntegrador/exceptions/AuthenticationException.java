package com.example.ProyectoIntegrador.exceptions;

import java.io.IOException;

public class AuthenticationException extends IOException {

    public AuthenticationException(String message) {
        super(message);
    }
}
