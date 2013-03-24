package pl.jagm.kanban.controllers

import spock.lang.Specification

public class MainControllerTest extends Specification {

    def "test index action"() {
        given:
        def mainController = new MainController()

        when:
        def result = mainController.index()

        then:
        result.getViewName() == 'index'
    }

}
