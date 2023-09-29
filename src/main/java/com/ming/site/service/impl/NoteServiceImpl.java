package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.model.Category;
import com.ming.site.model.Note;
import com.ming.site.model.User;
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
    public Note findById(long id) {
        Note note = super.findById(id);

        note = this.loadForeignField(note);
        return note;
    }

    @Override
    public List<Note> findAll() {
        QueryWrapper<Note> query = new QueryWrapper<>();
        query.orderByDesc("upgrade_at");

        List<Note> notes = repository.selectList(query);


        return this.loadForeign(notes);
    }

    public List<Note> loadForeign(List<Note> notes) {
        if (notes == null || notes.isEmpty()) {
            return null;
        }
        for (Note note : notes) {
            note = this.loadForeignField(note);
        }

        return notes;
    }

    public Note loadForeignField(Note note) {
        if (note == null) {
            return note;
        }
        Category category = categoryService.findById(note.getCategoryId());
        User createUser = userService.findById(note.getCreateUserId());
        User upgradeUser = userService.findById(note.getUpgradeUserId());

        note.setCategory(category);
        note.setCreateUser(createUser);
        note.setUpgradeUser(upgradeUser);
        return note;
    }

}
