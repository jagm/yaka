package pl.jagm.kanban.controllers

import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.WebDataBinder
import pl.jagm.kanban.dao.BoardDao
import pl.jagm.kanban.dao.IssueDao
import pl.jagm.kanban.dao.StateDao
import pl.jagm.kanban.dao.VersionDao
import pl.jagm.kanban.model.Issue
import pl.jagm.kanban.model.State
import pl.jagm.kanban.model.Version
import pl.jagm.kanban.model.propertiesEditors.VersionPropertyEditorSupport
import spock.lang.Specification

public class IssueControllerTest extends Specification {

    def issue = new Issue(id: 1, name: 'issue 1')
    def issueDao = Mock(IssueDao)
    def versionDao = Mock(VersionDao)
    def boardDao = Mock(BoardDao)
    def stateDao = Mock(StateDao)
    def issueController = new IssueController(issueDao, versionDao, boardDao, stateDao)

    def "test action read"() {
        given:
        def issuesList = [
                issue,
                new Issue(id: 2, name: 'issue 2')
        ]

        when:
        def result = issueController.read(7)

        then:
        1 * issueDao.list(7) >> issuesList
        result == issuesList
    }

    def "test action save issue"() {
        given:
        def bindingResult = Mock(BindingResult)
        def state = new State(id: 1, name: 'test state')

        when:
        def result = issueController.save(issue, 15, bindingResult)

        then:
        1 * bindingResult.hasErrors() >> false
        1 * stateDao.read(15) >> state
        1 * issueDao.create(issue)
        1 * stateDao.createIsueState({ it.state == state && it.issue == issue })
        result == [
                'errors': [],
                'issue': issue,
        ]
    }

    def "test action save invalid issue"() {
        given:
        def bindingResult = Mock(BindingResult)
        def errors = [
                new ObjectError("name", "message"),
                new ObjectError("name 2", "default message 2"),
        ]

        when:
        def result = issueController.save(issue, 15, bindingResult)

        then:
        with(bindingResult) {
            1 * hasErrors() >> true
            1 * getAllErrors() >> errors
        }
        result == [
                'errors': errors,
        ]
    }

    def "test action change state"() {
        given:
        def state = new State(id: 5, name: 'test state')

        when:
        def result = issueController.changeState(1, 5)

        then:
        with(stateDao) {
            1 * read(5) >> state
            1 * createIsueState({ it.state == state && it.issue == issue && it.created != null })
        }
        1 * issueDao.read(1) >> issue
        issue.states.size() == 1
        result == [
                'errors': [],
                'issue': issue
        ]

    }

    def "test binder initiation"() {
        given:
        def binder = Mock(WebDataBinder, { constructorArgs: [new Object()] })

        when:
        issueController.initBinder(binder)

        then:
        1 * binder.registerCustomEditor(Version.class, 'version', _ as VersionPropertyEditorSupport)
    }

}
