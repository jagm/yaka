package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class State implements Serializable {

    private int id;
    private String name;
    private Board board;
    private List<Issue> issues = new LinkedList<>();

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "board")
    public Board getBoard() {
        return board;
    }

    public void setBoard(@NotNull Board board) {
        this.board = board;
    }

    @NotNull
    @OneToMany(mappedBy = "state")
    public List<Issue> getIssues() {
        return Collections.unmodifiableList(issues);
    }

    public void setIssues(@NotNull List<Issue> issues) {
        this.issues = issues;
    }
}
