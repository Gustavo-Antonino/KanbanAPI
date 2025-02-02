package br.gustavo.antonino.KanbanAPI.controller;

import br.gustavo.antonino.KanbanAPI.model.Board;
import br.gustavo.antonino.KanbanAPI.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<Board> getAllBoards(){
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Long id){
        return boardService.getBoardById(id);
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @DeleteMapping("/{id}")
    public void deleteBoardById(@PathVariable Long id) {
        boardService.deleteBoardById(id);
    }

    @PutMapping("/{id}")
    public void updateNameBoardById(@PathVariable Long id, @RequestParam String name){
        boardService.updateNameBoardById(id, name);
    }
}
