package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Configuration;

@Component
@Transactional
public class ConfigurationDaoImpl extends AbstractDao implements ConfigurationDao {

    @Override
    public void create(@NotNull final Configuration configuration) {
        getCurrentSession().persist(configuration);
    }

    @Override
    public Configuration read(@NotNull final Long id) {
        return (Configuration) getCurrentSession().get(Configuration.class, id);
    }

    @Override
    public void update(@NotNull final Configuration configuration) {
        getCurrentSession().merge(configuration);
    }

    @Override
    public void delete(@NotNull final Configuration configuration) {
        getCurrentSession().delete(configuration);
    }
}
