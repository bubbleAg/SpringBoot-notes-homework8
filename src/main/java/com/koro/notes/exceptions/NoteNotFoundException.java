package com.koro.notes.exceptions;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(long id) {
        super("Could not found note with id = " + id);
    }
}
