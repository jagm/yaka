package pl.jagm.kanban.dao;

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
}
