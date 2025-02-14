package br.gustavo.antonino.KanbanAPI.repository;

import br.gustavo.antonino.KanbanAPI.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByBoardId(Long boardId);
}
