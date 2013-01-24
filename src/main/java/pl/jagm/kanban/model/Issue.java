package pl.jagm.kanban.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Issue implements Serializable {

    private static final String JIRA_PREFIX = "AVGM-";

    private int id;
    private String name;
    private Version version;
    private State state;
    private int jiraId;

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
    @JoinColumn(name = "version_id")
    public Version getVersion() {
        return version;
    }

    public void setVersion(@NotNull Version version) {
        this.version = version;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id")
    public State getState() {
        return state;
    }

    public void setState(@NotNull State state) {
        this.state = state;
    }

    public int getJiraId() {
        return jiraId;
    }

    public void setJiraId(int jiraId) {
        this.jiraId = jiraId;
    }
}
