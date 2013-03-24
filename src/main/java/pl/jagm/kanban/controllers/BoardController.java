package pl.jagm.kanban.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.jagm.kanban.dao.BoardDao;
import pl.jagm.kanban.dao.StateDao;
import pl.jagm.kanban.model.Board;
import pl.jagm.kanban.model.State;
import pl.jagm.kanban.model.propertiesEditors.BoardPropertyEditorSupport;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardDao boardDao;
    private final StateDao stateDao;

    @Autowired
    public BoardController(BoardDao boardDao, StateDao stateDao) {
        this.boardDao = boardDao;
        this.stateDao = stateDao;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@Valid Board board, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap();
        ArrayList<ObjectError> errors = new ArrayList();
        response.put("errors", errors);

        if (bindingResult.hasErrors()) {
            errors.addAll(bindingResult.getAllErrors());
        } else {
            boardDao.create(board);
            response.put("board", board);
            response.put("boards", boardDao.list());
        }

        return response;
    }

    @RequestMapping(value = "/add-state", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@Valid State state, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap();
        ArrayList<ObjectError> errors = new ArrayList();
        response.put("errors", errors);

        if (bindingResult.hasErrors()) {
            errors.addAll(bindingResult.getAllErrors());
        } else {
            stateDao.create(state);
            response.put("state", state);
        }

        return response;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Board.class, "board", new BoardPropertyEditorSupport(boardDao));
    }

}
