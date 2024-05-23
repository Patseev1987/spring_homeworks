package ru.bogdan.homework10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.bogdan.homework10.domain.SweetEater;
import ru.bogdan.homework10.repository.SweetEaterRepository;
import ru.bogdan.homework10.service.SweetEaterService;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SweetEaterServiceIntegrationTest {

@Autowired
    private SweetEaterService sweetEaterService;
@MockBean
    private SweetEaterRepository sweetEaterRepository;

    @Test
    public void sweetsTransferCorrectFlow(){
        //block of prepare
        var sender = SweetEater.builder()
                .id(1L)
                .name("John")
                .sweetsCount(100)
                .build();

        var receiver = SweetEater.builder()
                .id(2L)
                .name("Alice")
                .sweetsCount(30)
                .build();

        when(sweetEaterRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(sweetEaterRepository.findById(2L)).thenReturn(Optional.of(receiver));

        //block of  action
        sweetEaterService.doSweetsTransaction(1L,2L, 20 );

        //block of check
        verify(sweetEaterRepository).save(SweetEater.builder().id(1L).name("John").sweetsCount(80).build());
        verify(sweetEaterRepository).save(SweetEater.builder().id(2L).name("Alice").sweetsCount(50).build());
    }


    @Test
    public void checkDeleteEater(){

        sweetEaterService.deleteEater(20L);

        verify(sweetEaterRepository).deleteById(20L);
    }


    // we should set 2 times, because we have init data method
    @Test
    public void checkGetAllSweetEaters(){
        sweetEaterService.getAllSweetEaters();
        verify(sweetEaterRepository,times(2)).findAll();
    }
}
