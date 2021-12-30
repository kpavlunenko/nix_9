package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.Personal;

import java.util.Optional;

public interface PersonalService extends  BaseService<Personal> {
    Optional<Personal> findByEmail(String email);
}
