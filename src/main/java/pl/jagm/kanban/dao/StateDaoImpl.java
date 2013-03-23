package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.IssueState;
import pl.jagm.kanban.model.State;

@Component
@Transactional
public class StateDaoImpl extends AbstractDao implements StateDao {

    @Override
    public State read(@NotNull int id) {
        return (State) getCurrentSession().get(State.class, id);
    }

    @Override
    public void create(@NotNull State state) {
        getCurrentSession().persist(state);
    }

    @Override
    public void createIsueState(@NotNull IssueState issueState) {
        getCurrentSession().persist(issueState);
    }
}
