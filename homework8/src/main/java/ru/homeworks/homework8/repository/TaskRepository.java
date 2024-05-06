package ru.homeworks.homework8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homeworks.homework8.domain.Task;
import ru.homeworks.homework8.domain.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatus(TaskStatus status);

}
