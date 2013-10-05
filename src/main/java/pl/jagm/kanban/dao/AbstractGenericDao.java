package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Model;


@Transactional
public abstract class AbstractGenericDao<T extends Model> extends AbstractDao {

    protected abstract Class getObjectClass();

    public void create(@NotNull T object) {
        getCurrentSession().persist(object);
    }

    public T read(int id) {
        return (T) getCurrentSession().get(getObjectClass(), id);
    }

    public void update(@NotNull T object) {
        getCurrentSession().update(object);
    }

    public void delete(@NotNull T object) {
        getCurrentSession().delete(object);
    }


}
