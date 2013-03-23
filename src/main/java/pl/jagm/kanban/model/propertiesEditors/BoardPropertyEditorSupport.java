package pl.jagm.kanban.model.propertiesEditors;

import pl.jagm.kanban.dao.BoardDao;
import pl.jagm.kanban.model.Board;

import java.beans.PropertyEditorSupport;

public class BoardPropertyEditorSupport extends PropertyEditorSupport {

    private final BoardDao boardDao;

    public BoardPropertyEditorSupport(final BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public void setAsText(String text) {
        Board board = boardDao.read(Integer.parseInt(text));
        setValue(board);
    }
}
