package pl.jagm.kanban.model

import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

public class StateTest extends Specification {

    @Shared
    def Validator validator;

    def setup() {
        def ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def "test validation"() {
        given:
        def state = createState(name, board)

        when:
        def result = validator.validate(state)

        then:
        result.size() == size

        where:
        name   | board                  | size
        'test' | createBoard(1, 'test') | 0
    }

    def "test validation empty name"() {
        given:
        def state = createState('', createBoard(1, 'test'))

        when:
        def result = validator.validate(state)

        then:
        result.size() == 1
        result.iterator().next().getMessage() == 'size must be between 4 and 250'
    }

    def "test validation null name"() {
        given:
        def state = createState(null, createBoard(1, 'test'))

        when:
        def result = validator.validate(state)

        then:
        result.size() == 1
        def error = result.iterator().next()
        error.getPropertyPath().getLeafNode().getName() == 'name'
        error.getMessage() == 'may not be null'
    }

    def "test validation null board"() {
        given:
        def state = createState('test', null)

        when:
        def result = validator.validate(state)

        then:
        result.size() == 1
        def error = result.iterator().next()
        error.getPropertyPath().getLeafNode().getName() == 'board'
        error.getMessage() == 'may not be null'
    }

    def createBoard(final int id, final String name) {
        new Board(id: id, name: name)
    }

    def createState(final String name, final Board board) {
        new State(
                name: name,
                board: board
        )
    }

}
