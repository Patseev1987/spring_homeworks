package ru.diploma.homework5.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.homework5.domain.Task;
import ru.diploma.homework5.domain.TaskStatus;
import ru.diploma.homework5.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        task.setCreateDate(LocalDate.now());
      return   taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(TaskStatus status){
        return taskRepository.findAllByStatus(status);
    }

    public void  updateTaskStatusById(Long id, Task task){
        var oldTask = taskRepository.findById(id).orElse(null);
        if (oldTask != null){
            oldTask.setStatus(task.getStatus());
            taskRepository.save(oldTask);
        } else {
            new RuntimeException("Task with id " + id + " not found");
        }

    }
}
