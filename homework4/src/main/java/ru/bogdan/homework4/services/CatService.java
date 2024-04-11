package ru.bogdan.homework4.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bogdan.homework4.domain.Cat;
import ru.bogdan.homework4.repositories.CatRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CatService {
    private CatRepository catRepository;

    public List<Cat> getCats() {
        return catRepository.findAll();
    }

    public void deleteCatById(Long id){
        catRepository.deleteCatById(id);
    }

    public Cat updateCat(Cat cat){
        return catRepository.updateCat(cat);
    }
    public Cat addCat(Cat cat){
      return   catRepository.saveCat(cat);
    }
    public Cat getCatById(Long id){
        return  catRepository.findCatById(id);
    }
}
