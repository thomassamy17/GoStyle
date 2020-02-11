package fr.epsi.b3.monnaie.dao;

import fr.epsi.b3.monnaie.modele.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PromoDao {

    @PersistenceContext
    private EntityManager em;

}
