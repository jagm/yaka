package pl.jagm.kanban.dao

import org.hibernate.Session
import org.hibernate.SessionFactory
import pl.jagm.kanban.model.Issue
import pl.jagm.kanban.model.IssueState
import pl.jagm.kanban.model.State
import spock.lang.Specification

public class StateDaoImplTest extends Specification {

    def sessionFactory = Mock(SessionFactory)
    def session = Mock(Session)
    def stateDaoImpl = new StateDaoImpl(sessionFactory: sessionFactory)
    def state = new State(id: 2, name: "test")


    def "test read"() {
        when:
        def result = stateDaoImpl.read(17)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.get(State.class, 17) >> state
        result == state
    }

    def "test create"() {
        when:
        stateDaoImpl.create(state)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.persist(state)
    }

    def "test create issue state"() {
        def issueState = new IssueState(state: state, issue: new Issue(id: 2, name: "test issue"))
        when:
        stateDaoImpl.createIsueState(issueState)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.persist(issueState)
    }


}
