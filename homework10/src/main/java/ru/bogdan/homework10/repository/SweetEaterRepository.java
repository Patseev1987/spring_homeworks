package ru.bogdan.homework10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bogdan.homework10.domain.SweetEater;

public interface SweetEaterRepository  extends JpaRepository<SweetEater, Long> {
}
