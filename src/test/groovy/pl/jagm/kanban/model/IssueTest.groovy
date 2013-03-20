package pl.jagm.kanban.model

import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

public class IssueTest extends Specification {

    @Shared
    def Validator validator;

    def setup() {
        def ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def "test validation"() {
        given:
        def issue = createIssue(name, version)

        when:
        def result = validator.validate(issue)

        then:
        result.size() == size

        where:
        name   | version                  | size
        'test' | createVersion(1, 'test') | 0
    }

    def "test validation empty name"() {
        given:
        def issue = createIssue('', createVersion(1, 'test'))

        when:
        def result = validator.validate(issue)

        then:
        result.size() == 1
        result.iterator().next().getMessage() == 'size must be between 4 and 250'
    }

    def "test validation null name"() {
        given:
        def issue = createIssue(null, createVersion(1, 'test'))

        when:
        def result = validator.validate(issue)

        then:
        result.size() == 1
        def error = result.iterator().next()
        error.getPropertyPath().getLeafNode().getName() == 'name'
        error.getMessage() == 'may not be null'
    }

    def "test validation null version"() {
        given:
        def issue = createIssue('test', null)

        when:
        def result = validator.validate(issue)

        then:
        result.size() == 1
        def error = result.iterator().next()
        error.getPropertyPath().getLeafNode().getName() == 'version'
        error.getMessage() == 'may not be null'
    }

    def createVersion(final int id, final String name) {
        new Version(id: id, name: name)
    }

    def createIssue(final String name, final Version version) {
        new Issue(
                name: name,
                version: version
        )
    }

}
