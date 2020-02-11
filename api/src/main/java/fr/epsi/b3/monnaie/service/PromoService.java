package fr.epsi.b3.monnaie.service;

import fr.epsi.b3.monnaie.dao.PromoDao;
import fr.epsi.b3.monnaie.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PromoService {

    public String getByUser(String email, String password) {
        return email +" "+password;
    }
}
