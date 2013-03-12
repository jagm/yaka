package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Issue;

import java.util.List;

@Transactional
public class IssueDaoImpl extends AbstractDao implements IssueDao {

    @Override
    public void create(@NotNull Issue issue) {
        getCurrentSession().persist(issue);
    }

    @Override
    public Issue read(@NotNull int id) {
        return (Issue) getCurrentSession().get(Issue.class, id);
    }

    @Override
    public List<Issue> list(@NotNull int boardId) {
        String query =
                "select issue from Issue as issue join issue.states as issueState join issueState.state as state " +
                        "where " +
                        "state.board = " + boardId + " and " +
                        "issueState.created =" +
                        "(select max(issueState2.created) from IssueState as issueState2 where issueState2.issue = issue.id)";
        return getCurrentSession().createQuery(query).list();
    }

    @Override
    public void update(@NotNull Issue issue) {
        getCurrentSession().update(issue);
    }

    @Override
    public void delete(@NotNull Issue issue) {
        getCurrentSession().delete(issue);
    }
}
