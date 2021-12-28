package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.auth.SignupRequest;

public interface RegistrationFacade {

    void registration(SignupRequest dto);
}
