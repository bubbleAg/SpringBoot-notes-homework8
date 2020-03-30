package com.koro.notes;

import com.koro.notes.model.Note;
import com.koro.notes.repository.NoteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class NotesApplication {

    NoteRepository noteRepository;

    public NotesApplication(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void crate(){
        Note note = new Note();
        note.setArchived(false);
        note.setCreationDate(LocalDate.now());
        note.setLastUpdateDate(LocalDate.now());
        note.setText("Kupic mleko");
        note.setTitle("Lista zakupow");

        noteRepository.save(note);
    }

}
