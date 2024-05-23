package ru.bogdan.homework10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bogdan.homework10.domain.SweetEater;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SweetsTransfer {
   private SweetEater sender;
   private SweetEater receiver;
   private int amount;
}
