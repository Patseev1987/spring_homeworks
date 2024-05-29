package ru.patseev.homework11.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.homework11.domain.Note;
import ru.patseev.homework11.repositories.NoteRepositories;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepositories noteRepositories;

//    {
//        initData();
//    }

    // create new note in database and set create date
    public Note createNote(Note note) {
        note.setCreateDate(LocalDate.now());
        return noteRepositories.save(note);
    }
    // get all notes from database
    public List<Note> getAllNotes() {
        return noteRepositories.findAll();
    }
    // get note by ID from database
    public Optional<Note> getNote(long id) {
        return noteRepositories.findById(id);
    }
    // delete note by ID in database
    public void deleteNote(long id) {
        noteRepositories.deleteById(id);
    }
    //update note by id in database
    public Note updateNote(Long id, Note note) {
        Note noteBeforeUpdate = noteRepositories.findById(id).orElseThrow();
        noteBeforeUpdate.setCreateDate(LocalDate.now());
        noteBeforeUpdate.setContent(note.getContent());
        noteBeforeUpdate.setTitle(note.getTitle());
        return noteRepositories.save(noteBeforeUpdate);
    }

    //fill up the database
    private void initData(){
        new Thread(()-> {
            var notes = List.of(
                    Note.builder().title("say hello").content("Should say hello to my friends").build(),
                    Note.builder().title("wash dishes").content("Wash all dishes").build(),
                    Note.builder().title("fix code").content("should fix code").build(),
                    Note.builder().title("walk").content("Should walk with my wife").build()
            );
            try {
                Thread.sleep(500);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notes.forEach(this::createNote);
        }).start();
    }
}
