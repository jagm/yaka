package pl.jagm.kanban.model

import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

public class BoardTest extends Specification {

    @Shared
    def Validator validator;

    def setup() {
        def ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def "test validation"() {
        given:
        def board = createBoard('test')

        when:
        def result = validator.validate(board)

        then:
        result.size() == 0

    }

    def "test validation empty name"() {
        given:
        def board = createBoard('')

        when:
        def result = validator.validate(board)

        then:
        result.size() == 1
        result.iterator().next().getMessage() == 'size must be between 4 and 250'
    }

    def "test validation null name"() {
        given:
        def board = createBoard(null)

        when:
        def result = validator.validate(board)

        then:
        result.size() == 1
        def error = result.iterator().next()
        error.getPropertyPath().getLeafNode().getName() == 'name'
        error.getMessage() == 'may not be null'
    }

    def createBoard(final String name) {
        new Board(name: name, user: new AppUser())
    }

}
