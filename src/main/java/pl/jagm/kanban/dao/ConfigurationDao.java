package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import pl.jagm.kanban.model.Configuration;

public interface ConfigurationDao {

    public void create(@NotNull final Configuration configuration);

    public Configuration read(@NotNull final int id);

    public void update(@NotNull final Configuration configuration);

    public void delete(@NotNull final Configuration configuration);

}
