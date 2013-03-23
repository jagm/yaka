package pl.jagm.kanban.model.propertiesEditors

import pl.jagm.kanban.dao.BoardDao
import pl.jagm.kanban.model.Board
import spock.lang.Specification;

public class BoardPropertyEditorSupportTest extends Specification {

    def "test setAsAText"() {
        given:
        def board = new Board(id: 5, name: "test")
        def boardDao = Mock(BoardDao)
        def boardPropertyEditorSupport = new BoardPropertyEditorSupport(boardDao)

        when:
        boardPropertyEditorSupport.setAsText("5")

        then:
        1 * boardDao.read(5) >> board
    }

}
