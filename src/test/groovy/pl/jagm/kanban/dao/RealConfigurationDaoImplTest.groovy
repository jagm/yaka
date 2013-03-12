package pl.jagm.kanban.dao

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pl.jagm.kanban.model.Configuration
import spock.lang.Ignore
import spock.lang.Specification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:WEB-INF/applicationContext.xml")
@org.junit.Ignore
@Ignore
public class RealConfigurationDaoImplTest extends Specification {

    // workaround because @Shared doesn't work
    static lastId

    @Autowired(required = true)
    ConfigurationDao configurationDao;

    @Test
    def "test create object"() {
        given:
        Configuration configuration = new Configuration("test_name", "test_value")

        when:
        configurationDao.create(configuration)
        lastId = configuration.id

        then:
        configuration.id != null
    }


    @Test
    def "test read object"() {
        when:
        print 'aa=' + lastId
        def configuration = configurationDao.read(lastId)

        then:
        configuration.id == lastId
    }


    @Test
    def "test update object"() {
        given:
        def configuration = configurationDao.read(lastId)
        configuration.name = 'test_name_2'

        when:
        configurationDao.update(configuration)

        then:
        configurationDao.read(lastId).name == 'test_name_2'
    }


    @Test
    def "test delete object"() {
        given:
        def configuration = configurationDao.read(lastId)

        when:
        configurationDao.delete(configuration)

        then:
        configurationDao.read(lastId) == null
    }

}
