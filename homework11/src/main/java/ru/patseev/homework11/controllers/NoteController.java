package ru.patseev.homework11.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patseev.homework11.domain.Note;
import ru.patseev.homework11.services.NoteService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteController {
    private final NoteService noteService;
    //metrics for prometheus and grafana
    private final Counter getAllNotesCounter = Metrics.counter("get_all_notes");

    // get all notes
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> listNotes() {
        getAllNotesCounter.increment();
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    // if note with id present, will return note
    @GetMapping("/note/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        Note noteById;
        if (noteService.getNote(id).isPresent()) {
            noteById = noteService.getNote(id).get();
            return new ResponseEntity<>(noteById, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Note(), HttpStatus.NOT_FOUND);
        }
    }
    // if note.getTitle and note.getContent != null, will create note
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        if (note.getTitle() == null || note.getContent() == null) {
            return new ResponseEntity<>(new Note(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }
    // delete note
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  if note with id present, will update note
    @PutMapping("update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
        if (noteService.getNote(id).isPresent()) {
         Note noteAfterUpdate = noteService.updateNote(id, note);
            return new ResponseEntity<>(noteAfterUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Note(), HttpStatus.NOT_FOUND);
        }
    }
}
