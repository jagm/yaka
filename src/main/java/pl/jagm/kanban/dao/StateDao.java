package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import pl.jagm.kanban.model.IssueState;
import pl.jagm.kanban.model.State;

public interface StateDao {

    public State read(@NotNull final int id);

    public void create(@NotNull final State state);

    public void createIsueState(@NotNull final IssueState issueState);

}
