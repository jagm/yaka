package pl.jagm.kanban.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jagm.kanban.dao.VersionDao;
import pl.jagm.kanban.model.Version;

import java.util.List;

@Controller
@RequestMapping("/version")
public class VersionController {

    private final VersionDao versionDao;

    @Autowired
    public VersionController(VersionDao versionDao) {
        this.versionDao = versionDao;
    }

    @RequestMapping("/list")
    public
    @ResponseBody
    List<Version> getList() {
        return versionDao.list();
    }

}
