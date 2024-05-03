package ru.bogdan.homework7.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogdan.homework7.domain.Task;
import ru.bogdan.homework7.domain.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatus(TaskStatus status);

}
