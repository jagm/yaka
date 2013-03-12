package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Board;

import java.util.List;

@Component
@Transactional
public class BoardDaoImpl extends AbstractDao implements BoardDao {

    private final Logger logger = LoggerFactory.getLogger(BoardDaoImpl.class);

    @Override
    public void create(@NotNull Board board) {
        getCurrentSession().persist(board);
    }

    @Override
    public Board read(@NotNull int id) {
        Board board = (Board) getCurrentSession().get(Board.class, id);
        return board;
    }

    @Override
    public void update(@NotNull Board board) {
        getCurrentSession().update(board);
    }

    @Override
    public void delete(@NotNull Board board) {
        getCurrentSession().delete(board);
    }

    @Override
    public List<Board> list() {
        return getCurrentSession().createQuery("from Board").list();
    }

}
