package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Issue implements Serializable {

    private static final String JIRA_PREFIX = "AVGM-";

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "version_id")
    private Version version;

    private int jiraId;

    @OneToMany(mappedBy = "issue")
    private List<IssueState> states = new LinkedList<>();

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
    public Version getVersion() {
        return version;
    }

    public void setVersion(@NotNull Version version) {
        this.version = version;
    }

    public int getJiraId() {
        return jiraId;
    }

    public void setJiraId(int jiraId) {
        this.jiraId = jiraId;
    }

    @NotNull
    public List<IssueState> getStates() {
        return Collections.unmodifiableList(states);
    }

    public void setStates(@NotNull List<IssueState> states) {
        this.states = states;
    }
}
