package ru.homeworks.mymicroservicetools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homeworks.mymicroservicetools.domain.Tool;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, String> {
    Optional<Tool> findByCode(String code);
}
