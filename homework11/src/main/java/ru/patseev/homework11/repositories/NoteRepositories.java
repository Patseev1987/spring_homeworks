package ru.patseev.homework11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patseev.homework11.domain.Note;


public interface NoteRepositories extends JpaRepository<Note, Long> {
}
