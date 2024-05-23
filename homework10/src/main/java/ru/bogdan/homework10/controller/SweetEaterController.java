package ru.bogdan.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bogdan.homework10.domain.SweetEater;
import ru.bogdan.homework10.dto.SweetsTransfer;
import ru.bogdan.homework10.service.SweetEaterService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SweetEaterController {
    private final SweetEaterService sweetEaterService;

    @GetMapping("/eaters")
    public List<SweetEater> eaters() {
        return sweetEaterService.getAllSweetEaters();
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody SweetsTransfer transfer) {
        sweetEaterService.doSweetsTransaction(transfer.getSender().getId(),
                transfer.getReceiver().getId(),
                transfer.getAmount());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        sweetEaterService.deleteEater(id);
    }

    @GetMapping("/eaters/{id}")
    public SweetEater getEater(@PathVariable Long id) {
        return sweetEaterService.getSweetEater(id);
    }
}
