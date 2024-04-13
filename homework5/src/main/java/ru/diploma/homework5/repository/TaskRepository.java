package ru.diploma.homework5.repository;

import jakarta.persistence.NamedNativeQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.diploma.homework5.domain.Task;
import ru.diploma.homework5.domain.TaskStatus;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByStatus(TaskStatus status);

}
