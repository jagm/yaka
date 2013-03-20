package pl.jagm.kanban.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.jagm.kanban.dao.BoardDao;
import pl.jagm.kanban.dao.IssueDao;
import pl.jagm.kanban.dao.StateDao;
import pl.jagm.kanban.dao.VersionDao;
import pl.jagm.kanban.model.Issue;
import pl.jagm.kanban.model.IssueState;
import pl.jagm.kanban.model.State;
import pl.jagm.kanban.model.Version;
import pl.jagm.kanban.model.propertiesEditors.VersionPropertyEditorSupport;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueDao issueDao;
    private final VersionDao versionDao;
    private final StateDao stateDao;
    private final BoardDao boardDao;


    @Autowired
    public IssueController(IssueDao issueDao, VersionDao versionDao, BoardDao boardDao, StateDao stateDao) {
        this.issueDao = issueDao;
        this.versionDao = versionDao;
        this.boardDao = boardDao;
        this.stateDao = stateDao;
    }

    @RequestMapping("/list/{boardId}")
    public
    @ResponseBody
    List<Issue> read(@PathVariable("boardId") int boardId) {
        return issueDao.list(boardId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@Valid Issue issue, @RequestParam(value = "state_id") int stateId, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap();
        ArrayList<ObjectError> errors = new ArrayList();
        response.put("errors", errors);
        if (bindingResult.hasErrors()) {
            errors.addAll(bindingResult.getAllErrors());
        } else {
            State state = stateDao.read(stateId);
            IssueState issueState = new IssueState(issue, state);
            issue.addState(issueState);
            issueDao.create(issue);
            stateDao.createIsueState(issueState);
            response.put("issue", issue);
        }

        return response;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Version.class, "version", new VersionPropertyEditorSupport(versionDao));
    }

}
