package ru.diploma.homework5.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diploma.homework5.domain.Task;
import ru.diploma.homework5.domain.TaskStatus;
import ru.diploma.homework5.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping ("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTaskStatusById(id, task);
    }
}
