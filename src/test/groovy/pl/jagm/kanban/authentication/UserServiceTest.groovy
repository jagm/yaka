package pl.jagm.kanban.authentication

import org.springframework.security.core.userdetails.UsernameNotFoundException
import pl.jagm.kanban.dao.UserDao
import pl.jagm.kanban.model.AppUser
import spock.lang.Specification

public class UserServiceTest extends Specification {

    def userDao = Mock(UserDao)
    def userService = new UserService(userDao)

    def "test load user by not existing username"() {
        when:
        userService.loadUserByUsername("abc")

        then:
        1 * userDao.read("abc") >> null
        def e = thrown(UsernameNotFoundException)
        e.getMessage() == "abc not found"
    }

    def "test load user by username"() {
        given:
        def appUser = new AppUser(login: "jagm", password: "123")

        when:
        def user = userService.loadUserByUsername("jagm")

        then:
        1 * userDao.read("jagm") >> appUser
        user.getUsername() == "jagm"
        user.getAuthorities().size() == 2
    }

}
