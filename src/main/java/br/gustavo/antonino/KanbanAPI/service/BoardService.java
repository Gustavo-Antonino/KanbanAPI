package br.gustavo.antonino.KanbanAPI.service;

import br.gustavo.antonino.KanbanAPI.model.Board;
import br.gustavo.antonino.KanbanAPI.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository; // Injeção automática

    // Busca todos os boards.
    public List<Board> getAllBoard(){
        return boardRepository.findAll();
    }

    // Busca o board com base no seu id.
    public Board getBoardById(Long id){
        return boardRepository.findById(id).orElse(null);
    }

    // Cria um novo board.
    public Board createBoard(Board board){
        return boardRepository.save(board);
    }

    // Deleta o board pelo Id.
    public void deleteBoardById(Long id){
        boardRepository.deleteById(id);
    }

    // Atualizar o nome de um board com base no seu ID.
    public void updateNameBoardById(Long id, String name){

        // Busca o Board pelo ID e lança uma exceção caso não seja encontrado.
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board não encontrado com o Id: " + id));

        // Atualiza o nome do board.
        board.setName(name);

        // Salva as alterações no banco de dados.
        boardRepository.save(board);
    }
}
