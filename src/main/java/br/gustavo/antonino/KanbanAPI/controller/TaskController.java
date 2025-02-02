package br.gustavo.antonino.KanbanAPI.controller;

import br.gustavo.antonino.KanbanAPI.model.ColumnStatus;
import br.gustavo.antonino.KanbanAPI.model.Task;
import br.gustavo.antonino.KanbanAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards/{boardId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@PathVariable Long boardId, @RequestParam ColumnStatus status, @RequestBody Task task) {
        Task createdTask = taskService.createTask(task, status, boardId);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<Map<ColumnStatus, List<Task>>> getAllTasks(@PathVariable Long boardId) {
        Map<ColumnStatus, List<Task>> tasks = taskService.getAllTasks(boardId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskId, @RequestParam String name, @RequestParam String description) {
        taskService.updateTask(taskId, name, description);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        Task updatedTask = taskService.updateTaskStatus(taskId, status);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}