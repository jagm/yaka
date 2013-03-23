package pl.jagm.kanban.model;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class State implements Model {

    @Id
    @GeneratedValue
    private int id;

    @javax.validation.constraints.NotNull
    @Size(min = 4, max = 250)
    @Column(nullable = false)
    private String name;

    @javax.validation.constraints.NotNull
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "board")
    private Board board;

    @JsonManagedReference
    @JsonIgnore
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
