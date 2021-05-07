package com.reilgan.notes.controller;

import com.reilgan.notes.model.Note;
import com.reilgan.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(value = "/notes", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody Note note){
        return new ResponseEntity<>(noteService.create(note),
                                    HttpStatus.CREATED);
    }

    @GetMapping(value = "/notes")
    public ResponseEntity<?> read() {
        final List<Note> notes = noteService.readAll();
        return notes != null && !notes.isEmpty()
                ? new ResponseEntity<>(notes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/notes/{substring}")
    public ResponseEntity<?> searchBySubstring(@PathVariable() String substring){
        final List<Note> notes = noteService.searchBySubstring(substring);
        return notes != null && !notes.isEmpty()
                ? new ResponseEntity<>(notes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/notes/{id}")
    public void delete(@PathVariable("id") Integer id) {
        noteService.delete(id);
    }
}
