package ru.bogdan.sem5;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    private Service service;

    @GetMapping("/dogs")
    public List<Dog> getDogs() {
        return service.findAll();
    }

    @PostMapping("/dog")
    public void addDog(@RequestBody Dog dog) {
        service.save(dog);
    }

    
}