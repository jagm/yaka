package pl.jagm.kanban.model;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Board implements Model {


    @Id
    @GeneratedValue
    private int id;

    @javax.validation.constraints.NotNull
    @Size(min = 4, max = 250)
    @Column(nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<State> states = new LinkedList();

    @javax.validation.constraints.NotNull
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user")
    private AppUser user;

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
    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }

    public void setStates(@NotNull List<State> states) {
        this.states = states;
    }

    @NotNull
    public AppUser getUser() {
        return user;
    }

    public void setUser(@NotNull AppUser user) {
        this.user = user;
    }
}
