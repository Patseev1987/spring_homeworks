package ru.bogdan.homework4.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdan.homework4.domain.Cat;
import ru.bogdan.homework4.services.CatService;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/catsApi")
public class CatController {

    private CatService catService;


    public String getIndex() {
        return "index.html";
    }


    @GetMapping("/cats")
    public String findAll(Model model) {
        List<Cat> cats = catService.getCats();
        model.addAttribute("cats", cats);
        return "cat-list";
    }

    @GetMapping("/cat-create")
    public String createUserForm(Cat cat) {
        return "cat-create";
    }

    @PostMapping("/cat-create")
    public String createUser(Cat cat) {
        catService.addCat(cat);
        return "redirect:cats";
    }

    @GetMapping("/cat-delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        catService.deleteCatById(id);
        return "redirect:/catsApi/cats";
    }

    @GetMapping("/cat-update/{id}")
    public String createUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("cat", catService.getCatById(id));
        return "cat-update";
    }

    @PostMapping("/cat-update")
    public String updateUser(Cat cat) {
        catService.updateCat(cat);
        return "redirect:cats";
    }


}
