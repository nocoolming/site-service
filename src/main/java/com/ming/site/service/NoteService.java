package com.ming.site.service;

import com.ming.site.model.Note;

import java.util.List;

public interface NoteService
        extends CrudService<Note, Long> {

    List<Note> loadForeign(List<Note> notes);

    Note loadForeignField(Note note);
}
