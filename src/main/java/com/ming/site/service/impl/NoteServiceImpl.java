package com.ming.site.service.impl;

import com.ming.site.model.Note;
import com.ming.site.repository.NoteRepository;
import com.ming.site.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl
        extends AbstractService<Note, Long, NoteRepository>
        implements NoteService {
    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<Note> findAll(){
        List<Note> notes = super.findAll();

        return notes;
    }

}
