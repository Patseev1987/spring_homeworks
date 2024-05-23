package ru.bogdan.homework7.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bogdan.homework7.domain.Task;
import ru.bogdan.homework7.domain.TaskStatus;
import ru.bogdan.homework7.repository.TaskRepository;


import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    //init block of code for creat tasksTable
    {
      //  initData();
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        task.setCreateDate(LocalDate.now());
        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    public void updateTaskStatusById(Long id, Task task) {
        var oldTask = taskRepository.findById(id).orElse(null);
        if (oldTask != null) {
            oldTask.setStatus(task.getStatus());
            taskRepository.save(oldTask);
        } else {
            throw new RuntimeException("Task with id " + id + " not found");
        }

    }


    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    // function for init data. Just for tests
    private void initData() {
        new Thread(() -> {
            List<Task> t;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                t = taskRepository.findAll();
            } catch (Exception e) {
                t = null;
            }
            if (t == null || t.isEmpty()) {
                var task1 = new Task();
                task1.setDescription("Make a coffee");
                task1.setStatus(TaskStatus.NOT_STARTED);
                var task2 = new Task();
                task2.setDescription("Iron clothes");
                task2.setStatus(TaskStatus.IN_PROGRESS);
                var task3 = new Task();
                task3.setDescription("Call parents");
                task3.setStatus(TaskStatus.COMPLETED);

                var taskList = List.of(task1, task2, task3);

                for (var task : taskList) {
                    System.out.println("add task: " + task.getDescription());
                    addTask(task);
                }
            }
        }).start();
    }
}


