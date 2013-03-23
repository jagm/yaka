package pl.jagm.kanban.controllers

import pl.jagm.kanban.dao.VersionDao
import pl.jagm.kanban.model.Version
import spock.lang.Specification;

public class VersionControllerTest extends Specification {

    def versionDao = Mock(VersionDao)
    def versionController = new VersionController(versionDao)

    def "test action getList"() {
        given:
        def versionsList = [
                new Version(id: 1, name: 'version 2014'),
                new Version(id: 2, name: 'version 2015'),
        ]

        when:
        def result = versionController.getList()

        then:
        1 * versionDao.list() >> versionsList
        result == versionsList
    }

}
