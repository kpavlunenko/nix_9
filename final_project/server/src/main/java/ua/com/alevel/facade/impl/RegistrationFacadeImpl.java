package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.api.dto.request.auth.SignUpRequest;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.PersonalService;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalService personalService;

    public RegistrationFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(SignUpRequest signUpRequest) {
        Personal personal = new Personal();
        personal.setEmail(signUpRequest.getEmail());
        personal.setPassword(signUpRequest.getPassword());
        personal.setFirstName(signUpRequest.getFirstName());
        personal.setLastName(signUpRequest.getLastName());
        personalService.create(personal);
    }
}
