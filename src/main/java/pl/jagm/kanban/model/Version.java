package pl.jagm.kanban.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Version implements Model {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "version")
    private List<Issue> issues = new LinkedList<>();


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
    public List<Issue> getIssues() {
        return Collections.unmodifiableList(issues);
    }

    public void setIssues(@NotNull List<Issue> issues) {
        this.issues = issues;
    }
}
