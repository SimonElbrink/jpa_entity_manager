package se.lexicon.simon.jpa_entity_manager.database;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.simon.jpa_entity_manager.entity.AppUser;


import java.util.List;
import java.util.Optional;

public interface AppUserDao {
    @Transactional
    List<AppUser> findAll();

    @Transactional
    Optional<AppUser> findById(int id);

    @Transactional
    AppUser save(AppUser appUser);

    @Transactional
    void delete(int id);

    @Transactional
    AppUser update(AppUser appUser);
}
