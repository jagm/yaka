package pl.jagm.kanban.authentication;

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
import pl.jagm.kanban.model.Role;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser user = userDao.read(userName);

        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList();
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new User(user.getLogin(), user.getPassword(), true, true, true, !user.isDisabled(), authorities);
        }

        throw new UsernameNotFoundException(userName + " not found");
    }
}
