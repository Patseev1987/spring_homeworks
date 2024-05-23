package ru.bogdan.homework10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bogdan.homework10.domain.SweetEater;
import ru.bogdan.homework10.dto.SweetsTransfer;
import ru.bogdan.homework10.repository.SweetEaterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetEaterService {
    private final SweetEaterRepository eaterRepository;

    //init data
    {
        initData();
    }

    // do sweets transfer
    public void doSweetsTransaction(Long senderId, Long receiverId, Integer amount){
        var sender = eaterRepository.findById(senderId).orElseThrow();
        var receiver = eaterRepository.findById(receiverId).orElseThrow();
        sender.setSweetsCount(sender.getSweetsCount()-amount);
        receiver.setSweetsCount(receiver.getSweetsCount()+amount);
        eaterRepository.save(sender);
        eaterRepository.save(receiver);
    }

    // get all eaters
    public List<SweetEater> getAllSweetEaters () {
        return eaterRepository.findAll();
    }

    // delete eater
    public void deleteEater (Long id) {
         eaterRepository.deleteById(id);
    }

    // this code fills database
    private void initData() {
        new Thread(() -> {
            List<SweetEater> sweetEaters;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sweetEaters = eaterRepository.findAll();
            } catch (Exception e) {
                sweetEaters = null;
            }
            if (sweetEaters == null || sweetEaters.isEmpty()) {
                var eater1 = SweetEater.builder()
                        .name("John")
                        .sweetsCount(100)
                        .build();

                var eater2 = SweetEater.builder()
                        .name("Alice")
                        .sweetsCount(30)
                        .build();

                var eaterList = List.of(eater1, eater2);
                for (var eater : eaterList) {
                    System.out.println("add eater: " + eater);
                    eaterRepository.save(eater);
                }
            }
        }).start();
    }

}
