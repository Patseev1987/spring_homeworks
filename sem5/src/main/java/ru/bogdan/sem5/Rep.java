package ru.bogdan.sem5;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Rep extends JpaRepository<Dog,Long> {
}
