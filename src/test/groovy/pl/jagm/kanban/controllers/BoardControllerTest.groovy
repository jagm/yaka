package pl.jagm.kanban.controllers

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.WebDataBinder
import pl.jagm.kanban.authentication.MyUser
import pl.jagm.kanban.dao.BoardDao
import pl.jagm.kanban.dao.StateDao
import pl.jagm.kanban.dao.UserDao
import pl.jagm.kanban.model.AppUser
import pl.jagm.kanban.model.Board
import pl.jagm.kanban.model.State
import pl.jagm.kanban.model.propertiesEditors.BoardPropertyEditorSupport
import spock.lang.Specification


public class BoardControllerTest extends Specification {

    def board = new Board(id: 1, name: 'board 1')
    def boardDao = Mock(BoardDao)
    def stateDao = Mock(StateDao)
    def userDao = Mock(UserDao)
    def boardController = new BoardController(boardDao, stateDao, userDao)

    def "test action getList"() {
        given:
        'setup security context'()

        def boardsList = [
                board,
                new Board(id: 2, name: 'board 2')
        ]

        when:
        def result = boardController.getList()

        then:
        1 * boardDao.list(1) >> boardsList
        result == boardsList
    }

    def "test action read"() {
        when:
        def result = boardController.read(1)

        then:
        1 * boardDao.read(1) >> board
        result == board
    }

    def "test action save board"() {
        given:
        def bindingResult = Mock(BindingResult)
        def boards = [board, new Board(id: 2, name: 'test board 2')]
        'setup security context'()

        when:
        def result = boardController.save(board, bindingResult)

        then:
        1 * bindingResult.hasErrors() >> false
        1 * userDao.read(1) >> new AppUser(id: 1)
        with(boardDao) {
            1 * list() >> boards
            1 * create(board)
        }
        result == [
                'errors': [],
                'board': board,
                'boards': boards
        ]
    }

    def "test action save invalid board"() {
        given:
        def bindingResult = Mock(BindingResult)
        def errors = [
                new ObjectError("name", "message"),
                new ObjectError("name 2", "default message 2"),
        ]

        when:
        def result = boardController.save(board, bindingResult)

        then:
        with(bindingResult) {
            1 * hasErrors() >> true
            1 * getAllErrors() >> errors
        }
        result == [
                'errors': errors,
        ]
    }

    def "test action save state"() {
        given:
        def state = new State(id: 5, name: 'test state')
        def bindingResult = Mock(BindingResult)

        when:
        def result = boardController.save(state, bindingResult)

        then:
        1 * bindingResult.hasErrors() >> false
        1 * stateDao.create(state)
        result == [
                'errors': [],
                'state': state
        ]

    }

    def "test action save invalid state"() {
        given:
        def state = new State(id: 5, name: 'test state')
        def bindingResult = Mock(BindingResult)
        def errors = [
                new ObjectError("name", "message"),
                new ObjectError("name 2", "default message 2"),
        ]

        when:
        def result = boardController.save(state, bindingResult)

        then:
        with(bindingResult) {
            1 * hasErrors() >> true
            1 * getAllErrors() >> errors
        }
        result == [
                'errors': errors,
        ]
    }

    def "test binder initiation"() {
        given:
        def binder = Mock(WebDataBinder, { constructorArgs: [new Object()] })

        when:
        boardController.initBinder(binder)

        then:
        1 * binder.registerCustomEditor(Board.class, 'board', _ as BoardPropertyEditorSupport)
    }

    def 'setup security context'() {
        SecurityContextHolder.createEmptyContext()
        SecurityContextHolder.getContext().setAuthentication({
            principal: new MyUser(1, "test", "test", true, true, true, true, [])
        } as Authentication)
    }


}
