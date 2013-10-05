package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Version;

import java.util.List;

@Component
@Transactional
public class VersionDaoImpl extends AbstractGenericDao<Version> implements VersionDao {

    @Override
    public List<Version> list() {
        return getCurrentSession().createQuery("from Version").list();
    }

    @Override
    public void update(@NotNull Version object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(@NotNull Version object) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Class getObjectClass() {
        return Version.class;
    }
}
