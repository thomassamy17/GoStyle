package fr.epsi.b3.monnaie.dao;

import fr.epsi.b3.monnaie.modele.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsersDao {

    @PersistenceContext
    private EntityManager em;

    public User getByEmailPassword(String email, String password) {
        return em.createQuery("SELECT c FROM User c WHERE c.email = :email and c.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password",password)
                .getSingleResult();
    }
}
