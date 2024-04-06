package com.example.homework3.services;
import com.example.homework3.domain.User;
import com.example.homework3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository() {
        return repository;
    }

    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }

    public List<User> sortUsersByAge(List<User> users) {

//        return repository.getUsers().stream().sorted(Comparator.comparing(User::getAge))
//                .collect(Collectors.toList());


        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

}
