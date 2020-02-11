package fr.epsi.gostyle.model;

public class User {

    private int id;
    private String email;
    private String firstname;
    private String name;

    public User(int id, String email, String firstname, String name) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
