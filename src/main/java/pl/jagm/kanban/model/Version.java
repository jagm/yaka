package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Version implements Serializable {

    private int id;
    private String name;
    private List<Issue> issues = new LinkedList();

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
    @OneToMany(mappedBy = "version")
    public List<Issue> getIssues() {
        return Collections.unmodifiableList(issues);
    }

    public void setIssues(@NotNull List<Issue> issues) {
        this.issues = issues;
    }
}
