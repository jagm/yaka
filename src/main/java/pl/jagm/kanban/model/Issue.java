package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Issue implements Model {

    @Id
    @GeneratedValue
    private int id;

    @javax.validation.constraints.NotNull
    @Size(min = 4, max = 250)
    @Column(nullable = false)
    private String name;

    @Size(max = 3000)
    private String description = "";

    @javax.validation.constraints.NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "version_id")
    private Version version;

    private boolean deleted = false;

    private Color color = Color.YELLOW;

    @OneToMany(mappedBy = "issue", fetch = FetchType.EAGER)
    @OrderBy("created DESC")
    private List<IssueState> states = new LinkedList<>();

    private int importance = 0;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public Version getVersion() {
        return version;
    }

    public void setVersion(@NotNull Version version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @NotNull
    public Color getColor() {
        return color;
    }

    public void setColor(@NotNull Color color) {
        this.color = color;
    }

    @NotNull
    public List<IssueState> getStates() {
        return Collections.unmodifiableList(states);
    }

    public void setStates(@NotNull List<IssueState> states) {
        this.states = states;
    }

    public void addState(@NotNull IssueState state) {
        states.add(state);
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
