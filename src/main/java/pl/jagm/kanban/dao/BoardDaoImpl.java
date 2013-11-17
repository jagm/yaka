package pl.jagm.kanban.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Board;

import java.util.List;

@Component
@Transactional
public class BoardDaoImpl extends AbstractGenericDao<Board> implements BoardDao {

    private final Logger logger = LoggerFactory.getLogger(BoardDaoImpl.class);

    @Override
    public List<Board> list() {
        return getCurrentSession().createQuery("from Board").list();
    }

    @Override
    public List<Board> list(int userId) {
        return getCurrentSession().createQuery("from Board where user = ?")
                .setInteger(0, userId)
                .list();
    }

    @Override
    protected Class getObjectClass() {
        return Board.class;
    }
}
