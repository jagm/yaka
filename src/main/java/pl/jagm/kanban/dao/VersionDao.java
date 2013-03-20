package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import pl.jagm.kanban.model.Version;

import java.util.List;

public interface VersionDao {

    public List<Version> list();

    public Version read(@NotNull final int id);

}
