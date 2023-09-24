package com.ming.site.service.impl;

import com.ming.site.model.Note;
import com.ming.site.repository.NoteRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CrudService;
import com.ming.site.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl
        extends AbstractService<Note, Long, NoteRepository>
        implements NoteService {
    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);
}
