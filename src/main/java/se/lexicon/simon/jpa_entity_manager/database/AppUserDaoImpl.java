package se.lexicon.simon.jpa_entity_manager.database;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.simon.jpa_entity_manager.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDaoImpl {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public List<AppUser> findAll(){
        List<AppUser> result = new ArrayList<>();
        TypedQuery<AppUser> query =
                entityManager.createQuery("SELECT u FROM AppUser u", AppUser.class);

        result = query.getResultList();
        return result;
    }

    @Transactional
    public Optional<AppUser> findById(int id){
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser save (AppUser appUser){

        if(appUser.getId() != 0){
            update(appUser);
        }

       entityManager.persist(appUser);
       return appUser;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public List<AppUser> findByName(String name) {

        TypedQuery<AppUser> query = entityManager
                .createQuery("SELECT u FROM AppUser u WHERE u.name= ?1", AppUser.class);

        query.setParameter(1, name);

        return query.getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public List<AppUser> findByEmail(String email){

        TypedQuery<AppUser> query = entityManager
                .createQuery("SELECT u FROM AppUser u WHERE u.email= ?1", AppUser.class);

        query.setParameter(1, email);

        return  query.getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void delete (int id){
        Optional<AppUser> result = findById(id);

        entityManager.remove(result.orElseThrow(IllegalArgumentException::new));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser update (AppUser appUser){

        if(appUser.getId() == 0){
            return save(appUser);
        }

        return entityManager.merge(appUser);
    }

}
