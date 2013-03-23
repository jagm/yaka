package pl.jagm.kanban.model.propertiesEditors

import pl.jagm.kanban.dao.VersionDao
import pl.jagm.kanban.model.Version
import spock.lang.Specification;

public class VersionPropertyEditorSupportTest extends Specification {

    def "test setAsAText"() {
        given:
        def version = new Version(id: 5, name: "test")
        def versionDao = Mock(VersionDao)
        def versionPropertyEditorSupport = new VersionPropertyEditorSupport(versionDao)

        when:
        versionPropertyEditorSupport.setAsText("5")

        then:
        1 * versionDao.read(5) >> version
    }

}
