package pl.jagm.kanban.dao

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import spock.lang.*
import pl.jagm.kanban.model.Configuration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:WEB-INF/applicationContext.xml")
public class RealConfigurationDaoImplTest extends Specification {

    @Autowired(required = true)
    ConfigurationDao configurationDao;

    @Test
    def "test create object"() {
        given:
        Configuration configuration = new Configuration("test_name", "test_value")
        when:
        configurationDao.create(configuration)
        then:
        configuration.id != null
    }


    @Test
    def "test read object"() {
        when:
        def configuration = configurationDao.read(1)
        then:
        configuration.id == 1
    }


    @Test
    def "test update object"() {
        when:
        def configuration = configurationDao.read(1)
        configuration.name = 'test_name_2'
        configurationDao.update(configuration)
        configuration = configurationDao.read(1)
        then:
        configuration.name == 'test_name_2'
    }

}
