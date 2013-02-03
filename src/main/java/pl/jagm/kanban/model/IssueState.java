package pl.jagm.kanban.model;

import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class IssueState implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id")
    private State state;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private DateTime created;


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
    public DateTime getCreated() {
        return created;
    }

    public void setCreated(@NotNull DateTime created) {
        this.created = created;
    }

    @PrePersist
    protected void onCreate() {
        created = new DateTime();
    }
}
