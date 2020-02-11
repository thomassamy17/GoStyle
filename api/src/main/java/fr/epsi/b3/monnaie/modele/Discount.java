package fr.epsi.b3.monnaie.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Discount {

    @Id
    private int id;
    private int rate;
    private String item_name;
    private String code;
    private int utilisation_max;
    private String date_debut_valdite;
    private String date_fin_valdite;

    @ManyToMany(mappedBy = "Discount")
    private Set<User> employees = new HashSet<>();



}
