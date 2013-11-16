package pl.jagm.kanban.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class AppUser implements Model {

    @Id
    @GeneratedValue
    private int id;

    @javax.validation.constraints.NotNull
    @Size(min = 4, max = 20)
    @Column(nullable = false, unique = true)
    private String login;


    @javax.validation.constraints.NotNull
    @Column(nullable = false)
    private String password;

    private boolean disabled = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
