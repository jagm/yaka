package pl.jagm.kanban.dao

import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import pl.jagm.kanban.model.Version
import spock.lang.Specification

public class VersionDaoImplTest extends Specification {

    def sessionFactory = Mock(SessionFactory)
    def session = Mock(Session)
    def versionDaoImpl = new VersionDaoImpl(sessionFactory: sessionFactory)
    def version = new Version(id: 2, name: "test")


    def "test read"() {
        when:
        def result = versionDaoImpl.read(2)

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.get(Version.class, 2) >> version
        result == version
    }

    def "test list"() {
        given:
        def version2 = new Version(id: 3, name: 'version number 2')
        def query = Mock(Query)

        when:
        def result = versionDaoImpl.list()

        then:
        1 * sessionFactory.getCurrentSession() >> session
        1 * session.createQuery("from Version") >> query
        1 * query.list() >> [this.version, version2]
        result == [this.version, version2]
    }

    def "test delete"() {
        when:
        versionDaoImpl.delete(new Version())

        then:
        UnsupportedOperationException exception = thrown()
    }

    def "test update"() {
        when:
        versionDaoImpl.update(new Version())

        then:
        UnsupportedOperationException exception = thrown()
    }

}
