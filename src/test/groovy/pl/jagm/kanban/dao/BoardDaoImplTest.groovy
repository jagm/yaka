package pl.jagm.kanban.dao

import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import pl.jagm.kanban.model.Board
import spock.lang.Specification

public class BoardDaoImplTest extends Specification {

    def sessionFactory = Mock(SessionFactory)
    def session = Mock(Session)
    def boardDaoImpl = new BoardDaoImpl(sessionFactory: sessionFactory)
    def board = new Board(id: 2, name: "test")


    def "test create"() {
        when:
        boardDaoImpl.create(board)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.persist(board)
    }

    def "test read"() {
        when:
        def result = boardDaoImpl.read(17)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.get(Board.class, 17) >> board
        result == board
    }

    def "test update"() {
        when:
        boardDaoImpl.update(board)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.merge(board)
    }

    def "test delete"() {
        when:
        boardDaoImpl.delete(board)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.delete(board)
    }

    def "test list"() {
        given:
        def board2 = new Board(id: 3, name: 'board number 2')
        def query = Mock(Query)

        when:
        def result = boardDaoImpl.list()

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.createQuery("from Board") >> query
        1 * query.list() >> [board, board2]
        result == [board, board2]
    }

}
