package fr.epsi.b3.monnaie.service;

import fr.epsi.b3.monnaie.dao.UsersDao;
import fr.epsi.b3.monnaie.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsersService {

    @Autowired
    private UsersDao usersDao;

    @Transactional
    public User getByEmailPassword(String email, String password) {
        User user = usersDao.getByEmailPassword(email,password);
        return user;
    }
}
