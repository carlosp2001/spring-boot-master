package com.example.firstwebapp;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password) {
        // Se compara el valor enviado por la solicitud con el valor a igualar,
        // el método equalsIgnoreCase a que no se distinga entre mayúsculas y minúsculas
        boolean isValidUser = username.equalsIgnoreCase("carlosp2001");
        boolean isValidPassword = password.equalsIgnoreCase("abc123def");
        return isValidPassword && isValidUser;
    }
}
