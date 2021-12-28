package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.Optional;

@Repository
public interface UserRepository <ENTITY extends User> extends AbstractRepository<ENTITY> {

    Optional<ENTITY> findByEmail(String email);
    boolean existsByEmail(String email);
}
