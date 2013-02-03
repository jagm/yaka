package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class State implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "board")
    private Board board;

    @OneToMany(mappedBy = "state")
    private List<IssueState> issues = new LinkedList<>();

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
    public Board getBoard() {
        return board;
    }

    public void setBoard(@NotNull Board board) {
        this.board = board;
    }

    @NotNull
    public List<IssueState> getIssues() {
        return Collections.unmodifiableList(issues);
    }

    public void setIssues(@NotNull List<IssueState> issues) {
        this.issues = issues;
    }
}
