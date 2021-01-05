package se.lexicon.simon.jpa_entity_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import se.lexicon.simon.jpa_entity_manager.database.AppUserDao;
import se.lexicon.simon.jpa_entity_manager.entity.AppUser;

@Component
public class CommandLine implements CommandLineRunner {

    private AppUserDao appUserDao;

    @Autowired
    public CommandLine(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public void run(String... args) throws Exception {

        AppUser appUser = new AppUser(0, "Simon", "Simon@home.se");

        appUserDao.save(appUser);

    }
}
