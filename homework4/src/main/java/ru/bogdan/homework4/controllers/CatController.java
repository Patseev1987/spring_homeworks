package ru.bogdan.homework4.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdan.homework4.domain.Cat;
import ru.bogdan.homework4.services.CatService;

import java.io.IOException;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

@Log
@Controller
@AllArgsConstructor

public class CatController {

    private CatService catService;

@RequestMapping
    public String getIndex() throws IOException {
        log.info("return index.html");
        return "index.html";
    }


    @GetMapping("/cats")
    public String findAll(Model model) {
        List<Cat> cats = catService.getCats();
        model.addAttribute("cats", cats);
        log.info("return all cats");
        return "cat-list";
    }

    @GetMapping("/cat-create")
    public String createCatForm(Cat cat) {
    log.info("create cat form");
        return "cat-create";
    }

    @PostMapping("/cat-create")
    public String createCat(Cat cat) {
        catService.addCat(cat);
        log.info("redirect to /cats");
        return "redirect:/cats";
    }

    @GetMapping("/cat-delete/{id}")
    public String deleteCatById(@PathVariable("id") Long id) {
        catService.deleteCatById(id);
        log.info("cat was deleted and redirect to /cats");
        return "redirect:/cats";
    }

    @GetMapping("/cat-update/{id}")
    public String createCatForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("cat", catService.getCatById(id));
        log.info("create cat form for edit cat");
        return "cat-update";
    }

    @PostMapping("/cat-update")
    public String updateCat(Cat cat) {
        catService.updateCat(cat);
        log.info("cat was updated redirect to /cats");
        return "redirect:/cats";
    }


}
