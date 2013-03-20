package pl.jagm.kanban.model.propertiesEditors;

import pl.jagm.kanban.dao.VersionDao;
import pl.jagm.kanban.model.Version;

import java.beans.PropertyEditorSupport;

public class VersionPropertyEditorSupport extends PropertyEditorSupport {

    private final VersionDao versionDao;

    public VersionPropertyEditorSupport(final VersionDao versionDao) {
        this.versionDao = versionDao;
    }

    @Override
    public void setAsText(String text) {
        Version type = versionDao.read(Integer.parseInt(text));
        setValue(type);
    }
}
