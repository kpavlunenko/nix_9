package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.entity.PersonalRequestDto;
import ua.com.alevel.api.dto.response.entity.PersonalResponseDto;

public interface PersonalFacade extends BaseFacade<PersonalRequestDto, PersonalResponseDto>{
    PersonalResponseDto findByEmail(String email);
    void update(PersonalRequestDto req, String email);
}
