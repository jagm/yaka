package pl.jagm.kanban.dao

import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import pl.jagm.kanban.model.AppUser
import spock.lang.Specification

public class UserDaoImplTest extends Specification {

    def sessionFactory = Mock(SessionFactory)
    def session = Mock(Session)
    def userDaoImpl = new UserDaoImpl(sessionFactory: sessionFactory)

    def "test read by user name"() {
        given:
        def user = new AppUser(login: "jagm")
        def query = Mock(Query)

        when:
        def result = userDaoImpl.read("jagm")

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.createQuery("from AppUser as user where user.login = ?") >> query
        1 * query.setString(0, "jagm") >> query
        1 * query.uniqueResult() >> user
        result == user
    }

}
