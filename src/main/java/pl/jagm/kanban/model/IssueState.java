package pl.jagm.kanban.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class IssueState implements Model {

    @Id
    @GeneratedValue
    private int id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id")
    private State state;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime created;


    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(@NotNull Issue issue) {
        this.issue = issue;
    }

    @NotNull
    public State getState() {
        return state;
    }

    public void setState(@NotNull State state) {
        this.state = state;
    }

    @NotNull
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(@NotNull LocalDateTime created) {
        this.created = created;
    }

    @PrePersist
    protected void onCreate() {
        created = new LocalDateTime();
    }
}
