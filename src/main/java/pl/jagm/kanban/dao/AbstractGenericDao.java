package pl.jagm.kanban.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Model;


@Transactional
public abstract class AbstractGenericDao<T extends Model> {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    protected abstract Class getObjectClass();

    public void create(@NotNull T object) {
        getCurrentSession().persist(object);
    }

    public T read(int id) {
        return (T) getCurrentSession().get(getObjectClass(), id);
    }

    public void update(@NotNull T object) {
        getCurrentSession().merge(object);
    }

    public void delete(@NotNull T object) {
        getCurrentSession().delete(object);
    }

    @NotNull
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
