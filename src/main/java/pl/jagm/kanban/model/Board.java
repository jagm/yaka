package pl.jagm.kanban.model;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Board implements Model {


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<State> states = new LinkedList();

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
}
