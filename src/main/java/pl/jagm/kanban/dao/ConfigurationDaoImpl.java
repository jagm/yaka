package pl.jagm.kanban.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Configuration;

@Component
@Transactional
public class ConfigurationDaoImpl extends AbstractGenericDao<Configuration> implements ConfigurationDao {

    @Override
    protected Class getObjectClass() {
        return Configuration.class;
    }
}
