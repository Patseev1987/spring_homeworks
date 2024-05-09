package ru.homeworks.mymicroserverworkers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homeworks.mymicroserverworkers.domain.Worker;
import ru.homeworks.mymicroserverworkers.service.WorkerService;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/workers")
@RequiredArgsConstructor
public class WorkerRestController {
    private final WorkerService workerService;
    @GetMapping()
    public List<Worker> workers() {
        return workerService.findAllWorkers();
    }
    @GetMapping("/{id}")
    public Worker worker(@PathVariable Long id) {
        return workerService.findWorker(id);
    }
    @PostMapping("/create")
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.createWorker(worker);
    }
    @PutMapping("/update")
    public Worker updateWorker(@RequestParam Long id,@RequestBody Worker worker) {
        return workerService.updateWorker(id,worker);
    }
    @DeleteMapping("/delete")
    public void deleteWorker(@RequestParam Long id) {
        workerService.deleteWorker(id);
    }
}
