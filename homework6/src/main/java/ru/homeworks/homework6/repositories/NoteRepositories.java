package ru.homeworks.homework6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homeworks.homework6.domain.Note;

import java.util.Optional;

public interface NoteRepositories extends JpaRepository<Note, Long> {
}
