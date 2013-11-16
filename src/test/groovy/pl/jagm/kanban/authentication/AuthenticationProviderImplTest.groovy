package pl.jagm.kanban.authentication

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import spock.lang.Specification

public class AuthenticationProviderImplTest extends Specification {


    def "test authenticate wrong data"() {
        given:
        def authProvider = new AuthenticationProviderImpl()
        def auth = new UsernamePasswordAuthenticationToken(name, pass)

        when:
        def result = authProvider.authenticate(auth)

        then:
        result == null

        where:
        name   | pass
        "jagm" | "456"
        "abc"  | "123"
        "abc"  | "456"
    }

    def "test authenticate correct data"() {
        given:
        def authProvider = new AuthenticationProviderImpl()
        def auth = new UsernamePasswordAuthenticationToken("jagm", "123")

        when:
        def result = authProvider.authenticate(auth)

        then:
        result.name == "jagm"
        result.credentials == "123"
        result.authorities == [new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("guest")]

    }
}
