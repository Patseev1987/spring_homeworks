package ru.bogdan.sem5;

import lombok.AllArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {
  private  Rep  rep;

  public void save(Dog dog){
      rep.save(dog);
  }

  public List<Dog> findAll(){
      return rep.findAll();
  }
}
