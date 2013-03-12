package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import pl.jagm.kanban.model.Board;

import java.util.List;

public interface BoardDao {

    public void create(@NotNull final Board board);

    public Board read(@NotNull final int id);

    public List<Board> list();

    public void update(@NotNull final Board board);

    public void delete(@NotNull final Board board);

}
