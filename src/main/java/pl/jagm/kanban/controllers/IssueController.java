package pl.jagm.kanban.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jagm.kanban.dao.IssueDao;
import pl.jagm.kanban.model.Issue;

import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueDao issueDao;

    @Autowired
    public IssueController(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    @RequestMapping("/list/{boardId}")
    public
    @ResponseBody
    List<Issue> read(@PathVariable("boardId") int boardId) {
        return issueDao.list(boardId);
    }

}
