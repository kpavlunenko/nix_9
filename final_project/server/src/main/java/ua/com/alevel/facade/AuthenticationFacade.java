package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.auth.LoginRequest;
import ua.com.alevel.api.dto.response.auth.SecurityResponse;

public interface AuthenticationFacade {

    SecurityResponse login(LoginRequest dto);
}
