package ru.bogdan.homework2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bogdan.homework2.model.User;
import ru.bogdan.homework2.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUserById(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String createUserForm(Model model,@PathVariable("id") int id){
        model.addAttribute("user",userService.findUserById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users";
    }


}
