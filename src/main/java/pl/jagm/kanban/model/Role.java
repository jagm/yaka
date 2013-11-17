package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

@Entity
public class Role implements Model {

    @Id
    @GeneratedValue
    private int id;

    @javax.validation.constraints.NotNull
    @Size(min = 4, max = 250)
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "roles")
    private Set<AppUser> users;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public Set<AppUser> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public void setUsers(@NotNull Set<AppUser> users) {
        this.users = users;
    }

    public void addUser(@NotNull AppUser user) {
        users.add(user);
    }
}
