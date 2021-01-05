package se.lexicon.simon.jpa_entity_manager.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.simon.jpa_entity_manager.entity.AppUser;


import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AppUserDaoImplTest {

    @Autowired
    AppUserDao appUserDaoTest;

    AppUser appUserTest;
    String name = "Simon";
    String email = "Simon@jobb.se";

    @Before
    public void init() {
        appUserTest = new AppUser(name, email);
    }

    @Test
    public void given_AppUser_get_persisted() {

        AppUser persistedAppUser = appUserDaoTest.save(appUserTest);
        assertTrue(persistedAppUser.getId() > 0);
        assertTrue(appUserDaoTest.findById(persistedAppUser.getId()).isPresent());
    }

}
