package pl.jagm.kanban.authentication

import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification

public class UserServiceTest extends Specification {

    def "test load user by not existing username"() {
        given:
        def userService = new UserService()

        when:
        userService.loadUserByUsername("abc")

        then:
        def e = thrown(UsernameNotFoundException)
        e.getMessage() == "abc not found"
    }

    def "test load user by username"() {
        given:
        def userService = new UserService()

        when:
        def user = userService.loadUserByUsername("jagm")

        then:
        user.getUsername() == "jagm"
        user.getAuthorities().size() == 2
    }

}
