package com.koro.notes.controller;

import com.koro.notes.exceptions.NoteNotFoundException;
import com.koro.notes.model.Note;
import com.koro.notes.repository.NoteRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/notes")
public class NoteController {

    NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Note>> getAllNotes() {
        List<Note> noteList = noteRepository.findAll();
        noteList.forEach(car -> car.add(linkTo(NoteController.class).slash(car.getId()).withSelfRel()));
        Link link = linkTo(NoteController.class).withSelfRel();
        CollectionModel<Note> noteCollection = new CollectionModel<>(noteList, link);
        return new ResponseEntity<>(noteCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Note>> getNoteById(@PathVariable long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        Link link = linkTo(NoteController.class).withSelfRel();
        EntityModel<Note> noteEntityModel = new EntityModel<>(note, link);
        return new ResponseEntity<>(noteEntityModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        Note savedNote = noteRepository.save(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNote(@PathVariable long id, @RequestBody Note note) {
        note.setId(id);
        noteRepository.save(note);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<Note> archiveNote(@PathVariable long id){
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        note.setArchived(true);
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable long id){
        noteRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
