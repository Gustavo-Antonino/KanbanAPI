package br.gustavo.antonino.KanbanAPI.repository;

import br.gustavo.antonino.KanbanAPI.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
