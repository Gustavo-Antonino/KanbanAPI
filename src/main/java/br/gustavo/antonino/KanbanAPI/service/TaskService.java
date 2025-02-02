package br.gustavo.antonino.KanbanAPI.service;

import br.gustavo.antonino.KanbanAPI.model.Board;
import br.gustavo.antonino.KanbanAPI.model.ColumnStatus;
import br.gustavo.antonino.KanbanAPI.model.Task;
import br.gustavo.antonino.KanbanAPI.repository.BoardRepository;
import br.gustavo.antonino.KanbanAPI.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private BoardRepository boardRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }

    // Cria uma nova task dentro de uma coluna específica.
    public Task createTask(Task task, ColumnStatus status, Long boardId) {
        // Busca o board pelo ID
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board não encontrado"));

        // Associa o board e o status (coluna) à task
        task.setBoard(board);
        task.setStatus(status);

        // Atualiza a lista de tasks do board
        board.getTasks().add(task);

        // Salva a task no repositório
        return taskRepository.save(task);
    }

    // Busca todas as tasks que são relacionadas ao board, organizadas por coluna.
    public Map<ColumnStatus, List<Task>> getAllTasks(Long id) {
        // Busca todas as tasks relacionadas ao board
        List<Task> tasks = taskRepository.findByBoardId(id);

        // Organiza as tasks por status (coluna)
        Map<ColumnStatus, List<Task>> tasksByStatus = new HashMap<>();

        for (Task task : tasks) {
            ColumnStatus status = task.getStatus();
            tasksByStatus.computeIfAbsent(status, k -> new ArrayList<>()).add(task);
        }

        return tasksByStatus;
    }

    // Atualiza uma task (nome e descrição) com base no id.
    @Transactional
    public void updateTask(Long id, String name, String description){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task não encontrada com o Id: " + id));
        task.setName(name);
        task.setDescription(description);
    }

    @Transactional
    public Task updateTaskStatus(Long id, String status){
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task não encontrada com o Id: " + id));
        task.setStatus(ColumnStatus.valueOf(status));
        return task;
    }

    // Deleta uma task com base no Id.
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
