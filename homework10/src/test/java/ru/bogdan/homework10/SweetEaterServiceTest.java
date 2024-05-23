package ru.bogdan.homework10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bogdan.homework10.domain.SweetEater;
import ru.bogdan.homework10.repository.SweetEaterRepository;
import ru.bogdan.homework10.service.SweetEaterService;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SweetEaterServiceTest {
    @InjectMocks
    private SweetEaterService sweetEaterService;
    @Mock
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

        given(sweetEaterRepository.findById(1L)).willReturn(Optional.of(sender));
        given(sweetEaterRepository.findById(2L)).willReturn(Optional.of(receiver));

        //block of  action
        sweetEaterService.doSweetsTransaction(1L,2L, 20 );

        //block of check
        verify(sweetEaterRepository).save(SweetEater.builder().id(1L).name("John").sweetsCount(80).build());
        verify(sweetEaterRepository).save(SweetEater.builder().id(2L).name("Alice").sweetsCount(50).build());
    }


    @Test
    public void eaterNotFoundById(){
        given(sweetEaterRepository.findById(100L)).willThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () ->   sweetEaterService.getSweetEater(100L));

        verify(sweetEaterRepository).findById(100L);
    }

    @Test
    public void checkDeleteEater(){

        sweetEaterService.deleteEater(20L);

        verify(sweetEaterRepository).deleteById(20L);
    }

    @Test
    public void checkGetAllSweetEaters(){
        sweetEaterService.getAllSweetEaters();
        verify(sweetEaterRepository).findAll();
    }

}
