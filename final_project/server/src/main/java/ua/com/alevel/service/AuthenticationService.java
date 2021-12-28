package ua.com.alevel.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication login(String username, String password);
}
