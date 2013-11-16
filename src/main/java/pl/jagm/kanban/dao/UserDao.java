package pl.jagm.kanban.dao;

import org.jetbrains.annotations.NotNull;
import pl.jagm.kanban.model.AppUser;

public interface UserDao {

    AppUser read(@NotNull final int id);

    AppUser read(@NotNull final String login);

}
