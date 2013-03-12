package pl.jagm.kanban.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jagm.kanban.dao.BoardDao;
import pl.jagm.kanban.model.Board;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardDao boardDao;

    @Autowired
    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping("/list")
    public
    @ResponseBody
    List<Board> getList() {
        List<Board> boards = boardDao.list();
        return boards;
    }

    @RequestMapping("/read/{id}")
    public
    @ResponseBody
    Board read(@PathVariable("id") int id) {
        Board board = boardDao.read(id);
        return board;
    }

}
