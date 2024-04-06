package com.example.homework3.controllers;

import com.example.homework3.domain.User;
import com.example.homework3.services.DataProcessingService;
import com.example.homework3.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }

    @GetMapping("/sort")//localhost:1337/tasks/sort
    public List<User> sortUsersByAge()
    {
        return service.getDataProcessingService().sortUsersByAge(
                service.getDataProcessingService().getRepository().getUsers()
        );
    }

    //метод filterUsersByAge
    //Подсказка  @GetMapping("/filter/{age}") +
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable int age){
        return service.getDataProcessingService().filterUsersByAge(
                service.getDataProcessingService().getRepository().getUsers(),
                age
        );
    }

    //метод calculateAverageAge
    //Подсказка  @GetMapping("/calc") +
    @GetMapping("/calc")
    public Double calcUsersByAge(){
        return service.getDataProcessingService().calculateAverageAge(
                service.getDataProcessingService().getRepository().getUsers()
        );
    }
}
