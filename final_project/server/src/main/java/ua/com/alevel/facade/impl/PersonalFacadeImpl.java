package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.entity.PersonalRequestDto;
import ua.com.alevel.api.dto.response.entity.PersonalResponseDto;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.PersonalService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonalFacadeImpl implements PersonalFacade {

    private final PersonalService personalService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonalFacadeImpl(PersonalService personalService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personalService = personalService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(PersonalRequestDto personalRequestDto) {

    }

    @Override
    public void update(PersonalRequestDto personalRequestDto, Long id) {

    }

    @Override
    public void update(PersonalRequestDto personalRequestDto, String email) {
        Personal personal = personalService.findByEmail(email).get();
        personal.setFirstName(personalRequestDto.getFirstName());
        personal.setLastName(personalRequestDto.getLastName());
        personal.setBirthDay(personalRequestDto.getBirthDay());
        personal.setEnabled(personalRequestDto.getEnabled());
        personal.setRoleType(personalRequestDto.getRoleType());
        if (StringUtils.isNotEmpty(personalRequestDto.getPassword())) {
            personal.setPassword(bCryptPasswordEncoder.encode(personalRequestDto.getPassword()));
        }
        personalService.update(personal);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PersonalResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PersonalResponseDto findByEmail(String email) {
        return new PersonalResponseDto(personalService.findByEmail(email).get());
    }

    @Override
    public List<PersonalResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Personal> all = personalService.findAll(parameterMap);
        List<PersonalResponseDto> items = all.stream().map(PersonalResponseDto::new).collect(Collectors.toList());
        return items;
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return personalService.count(parameterMap);
    }
}
