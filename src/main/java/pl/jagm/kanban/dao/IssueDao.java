package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import pl.jagm.kanban.model.Issue;

import java.util.List;

public interface IssueDao {

    public void create(@NotNull final Issue issue);

    public Issue read(@NotNull final int id);

    public void update(@NotNull final Issue issue);

    public void delete(@NotNull final Issue issue);

    public List<Issue> list(@NotNull final int boardId);

}
