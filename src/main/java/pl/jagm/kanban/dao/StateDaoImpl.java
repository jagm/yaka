package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.IssueState;
import pl.jagm.kanban.model.State;

@Component
@Transactional
public class StateDaoImpl extends AbstractGenericDao<State> implements StateDao {

    @Override
    public void createIsueState(@NotNull IssueState issueState) {
        getCurrentSession().persist(issueState);
    }

    @Override
    protected Class getObjectClass() {
        return State.class;
    }
}
