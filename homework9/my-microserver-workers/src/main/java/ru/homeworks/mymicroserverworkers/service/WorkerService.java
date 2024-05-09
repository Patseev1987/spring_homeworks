package ru.homeworks.mymicroserverworkers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homeworks.mymicroserverworkers.domain.Worker;
import ru.homeworks.mymicroserverworkers.repository.WorkerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }
    public Worker findWorker(long id) {
        return workerRepository.findById(id).orElse(null);
    }
    public List<Worker> findAllWorkers() {
        return workerRepository.findAll();
    }
    public Worker updateWorker(Long id, Worker worker) {
        var oldWorker = findWorker(id);
        oldWorker.setFirstName(worker.getFirstName());
        oldWorker.setLastName(worker.getLastName());
        oldWorker.setPatronymic(worker.getPatronymic());
        return workerRepository.save(oldWorker);
    }
    public void deleteWorker(long id) {
        workerRepository.deleteById(id);
    }
}
