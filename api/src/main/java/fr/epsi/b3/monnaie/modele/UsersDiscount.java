package fr.epsi.b3.monnaie.modele;

import javax.persistence.*;

@Entity
@Table(name = "UsersDiscount")
public class UsersDiscount {

    @Id
    @GeneratedValue
    private int id;
    private User user;
    private Discount discount;

    private int nb_utilisation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id")
    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_utilisation() {
        return nb_utilisation;
    }

    public void setNb_utilisation(int nb_utilisation) {
        this.nb_utilisation = nb_utilisation;
    }
}
