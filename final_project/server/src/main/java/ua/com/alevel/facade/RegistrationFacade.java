package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.auth.SignUpRequest;

public interface RegistrationFacade {

    void registration(SignUpRequest dto);
}
