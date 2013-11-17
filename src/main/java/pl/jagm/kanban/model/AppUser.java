package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Role> roles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @NotNull
    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public void setRoles(@NotNull List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(@NotNull Role role) {
        roles.add(role);
    }
}
