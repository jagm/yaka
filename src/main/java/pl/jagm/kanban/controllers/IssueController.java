package pl.jagm.kanban.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
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

    private static final Logger log = LoggerFactory.getLogger(IssueController.class);

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

    @RequestMapping(value = "/change-state", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> changeState(@RequestParam(value = "issue_id") int issueId, @RequestParam(value = "state_id") int stateId) {
        Map<String, Object> response = new HashMap();
        ArrayList<ObjectError> errors = new ArrayList();
        response.put("errors", errors);

        Issue issue = issueDao.read(issueId);
        State state = stateDao.read(stateId);

        IssueState issueState = new IssueState(issue, state);
        issue.addState(issueState);
        stateDao.createIsueState(issueState);
        response.put("issue", issue);

        return response;
    }

    @RequestMapping(value = "/change-order", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> changeOrder(@RequestParam(value = "order[]") List<Integer> order) {
        Map<String, Object> response = new HashMap();
        ArrayList<ObjectError> errors = new ArrayList();
        response.put("errors", errors);

        log.info("Changing order for issues {}", order);

        int counter = 0;
        for (int issueId : order) {
            ++counter;
            Issue issue = issueDao.read(issueId);
            issue.setImportance(counter);
            issueDao.update(issue);
        }

        log.info("Order changed for issues {}", order);

        response.put("status", "OK");
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean delete(@RequestParam(value = "issue_id") int issueId) {
        issueDao.delete(issueId);
        return true;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Version.class, "version", new VersionPropertyEditorSupport(versionDao));
    }

}
