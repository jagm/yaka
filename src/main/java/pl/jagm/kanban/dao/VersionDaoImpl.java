package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jagm.kanban.model.Version;

import java.util.List;

@Component
@Transactional
public class VersionDaoImpl extends AbstractDao implements VersionDao {

    @Override
    public List<Version> list() {
        return getCurrentSession().createQuery("from Version").list();
    }

    @Override
    public Version read(@NotNull int id) {
        return (Version) getCurrentSession().get(Version.class, id);
    }
}
