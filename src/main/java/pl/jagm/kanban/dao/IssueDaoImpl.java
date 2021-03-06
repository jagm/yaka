package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Issue;

import java.util.List;

@Component
@Transactional
public class IssueDaoImpl extends AbstractGenericDao<Issue> implements IssueDao {

    private static final String LIST_QUERY = "SELECT issue FROM Issue AS issue JOIN issue.states AS issueState JOIN issueState.state AS state WHERE state.board = %s AND issue.deleted = false AND issueState.created = (SELECT MAX(issueState2.created) FROM IssueState AS issueState2 WHERE issueState2.issue = issue.id) ORDER BY importance";

    @Override
    public void delete(@NotNull Issue issue) {
        delete(issue.getId());
    }

    @Override
    public void delete(int id) {
        Issue issue = read(id);
        issue.setDeleted(true);
        update(issue);
    }

    @Override
    public List<Issue> list(int boardId) {
        String query = String.format(LIST_QUERY, Integer.toString(boardId));
        return getCurrentSession().createQuery(query).list();
    }

    protected Class getObjectClass() {
        return Issue.class;
    }

}
