package pl.jagm.kanban.authentication;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jagm.kanban.dao.UserDao;
import pl.jagm.kanban.model.AppUser;

import java.util.List;


@Service("userService")
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = ImmutableList.of(
            (GrantedAuthority) new SimpleGrantedAuthority("admin"),
            (GrantedAuthority) new SimpleGrantedAuthority("guest")
    );

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser user = userDao.read(userName);

        if (user != null) {
            return new User(user.getLogin(), user.getPassword(), true, true, true, !user.isDisabled(), DEFAULT_AUTHORITIES);
        }

        throw new UsernameNotFoundException(userName + " not found");
    }
}
