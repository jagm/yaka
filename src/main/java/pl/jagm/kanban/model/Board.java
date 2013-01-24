package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Board implements Serializable {


    private int id;
    private String name;
    private List<State> states = new LinkedList();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    @OneToMany(mappedBy = "board")
    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }

    public void setStates(@NotNull List<State> states) {
        this.states = states;
    }
}
