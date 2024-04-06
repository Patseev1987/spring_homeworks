package com.example.homework3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {


    @Autowired
    private DataProcessingService dataProcessingService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }


    //Поля UserService, NotificationService ++

    //Метод processRegistration +

    public void processRegistration(String name, int age, String email){
        var user = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        var msg = String.format("User\nname = %s\nage = %d\nemail = %s", user.getName(), user.getAge(), user.getEmail());
        notificationService.sendNotification(msg);
    }


}
