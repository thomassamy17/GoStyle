package fr.epsi.b3.monnaie.controleur;

import fr.epsi.b3.monnaie.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/users")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @GetMapping(path="/{email}/{password}", produces = "application/json")
    public String getById(@PathVariable String email, @PathVariable String password) {
        return promoService.getByUser(email,password);
    }


}
