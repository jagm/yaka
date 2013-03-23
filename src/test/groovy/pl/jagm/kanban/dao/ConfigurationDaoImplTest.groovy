package pl.jagm.kanban.dao

import org.hibernate.Session
import org.hibernate.SessionFactory
import pl.jagm.kanban.model.Configuration
import spock.lang.Specification

public class ConfigurationDaoImplTest extends Specification {

    def sessionFactory = Mock(SessionFactory)
    def configurationDao = new ConfigurationDaoImpl(sessionFactory: sessionFactory)

    def "create test"() {
        given:
        def configuration = new Configuration(
                name: 'test name',
                value: 'test value'
        )
        def session = Mock(Session)

        when:
        configurationDao.create(configuration)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.persist(configuration)
    }

    def "read test"() {
        given:
        def configuration = new Configuration(
                id: 5,
                name: 'test name',
                value: 'test value'
        )
        def session = Mock(Session)

        when:
        def conf = configurationDao.read(5)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.get(Configuration.class, 5) >> configuration
        conf == configuration

    }


    def "update test"() {
        given:
        def configuration = new Configuration(
                name: 'new test name',
                value: 'new test value'
        )
        def session = Mock(Session)

        when:
        configurationDao.update(configuration)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.merge(configuration)
    }

    def "delete test"() {
        given:
        def configuration = new Configuration(
                id: 7,
                name: 'test name',
                value: 'test value'
        )
        def session = Mock(Session)

        when:
        configurationDao.delete(configuration)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.delete(configuration)
    }


}
