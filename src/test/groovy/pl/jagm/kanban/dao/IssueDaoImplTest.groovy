package pl.jagm.kanban.dao

import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import pl.jagm.kanban.model.Issue
import spock.lang.Specification

public class IssueDaoImplTest extends Specification {

    def sessionFactory = Mock(SessionFactory)
    def session = Mock(Session)
    def issueDaoImpl = new IssueDaoImpl(sessionFactory: sessionFactory)
    def issue = new Issue(id: 7, name: "test issue")


    def "test create"() {
        when:
        issueDaoImpl.create(issue)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.persist(issue)
    }

    def "test read"() {
        when:
        def result = issueDaoImpl.read(7)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.get(Issue.class, 7) >> issue
        result == issue
    }

    def "test update"() {
        when:
        issueDaoImpl.update(issue)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.update(issue)
    }

    def "test delete"() {
        when:
        issueDaoImpl.delete(issue)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.delete(issue)
    }

    def "test list"() {
        given:
        def issue2 = new Issue(id: 3, name: 'issue number 2')
        def query = Mock(Query)

        when:
        def result = issueDaoImpl.list(19)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.createQuery(String.format(IssueDaoImpl.LIST_QUERY, '19')) >> query
        1 * query.list() >> [issue, issue2]
        result == [issue, issue2]
    }

}
