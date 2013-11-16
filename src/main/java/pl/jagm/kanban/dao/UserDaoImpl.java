package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.AppUser;

@Repository
@Transactional
public class UserDaoImpl extends AbstractGenericDao<AppUser> implements UserDao {

    @Override
    public AppUser read(@NotNull String login) {
        return (AppUser) getCurrentSession().createQuery("from AppUser as user where user.login = ?")
                .setString(0, login).uniqueResult();
    }

    @Override
    protected Class getObjectClass() {
        return AppUser.class;
    }

    @Override
    public void update(@NotNull AppUser object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(@NotNull AppUser object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(@NotNull AppUser object) {
        throw new UnsupportedOperationException();
    }
}
