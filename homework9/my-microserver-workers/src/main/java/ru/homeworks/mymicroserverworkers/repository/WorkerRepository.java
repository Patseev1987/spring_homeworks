package ru.homeworks.mymicroserverworkers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homeworks.mymicroserverworkers.domain.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
