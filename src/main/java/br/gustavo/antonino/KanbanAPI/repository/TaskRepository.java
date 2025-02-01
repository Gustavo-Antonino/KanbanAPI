package br.gustavo.antonino.KanbanAPI.repository;

import br.gustavo.antonino.KanbanAPI.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
