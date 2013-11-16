package pl.jagm.kanban.authentication;

import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserService implements UserDetailsService {

    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = ImmutableList.of(
            (GrantedAuthority) new SimpleGrantedAuthority("admin"),
            (GrantedAuthority) new SimpleGrantedAuthority("guest")
    );

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if ("jagm".equals(userName)) {
            return new User("jagm", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", true, true, true, true, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException(userName + " not found");
    }
}
