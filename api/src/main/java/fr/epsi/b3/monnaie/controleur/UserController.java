package fr.epsi.b3.monnaie.controleur;

import fr.epsi.b3.monnaie.modele.User;
import fr.epsi.b3.monnaie.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping(path="/{email}/{password}", produces = "application/json")
    public User getById(@PathVariable String email, @PathVariable String password) {
        return usersService.getByEmailPassword(email,password);
    }


}
