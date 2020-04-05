package com.koro.notes.repository;

import com.koro.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByArchivedTrue();
    List<Note> findAllByArchivedFalse();
}
