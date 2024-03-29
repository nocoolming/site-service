package com.ming.site.service.impl;


import com.ming.site.mapper.NoteMapper;
import com.ming.site.model.Category;
import com.ming.site.model.Note;
import com.ming.site.model.User;
import com.ming.site.service.*;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl
        extends AbstractService<Note, Long, NoteMapper>
        implements NoteService {
    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Override
    public Note findById(Long id) {
        Note note = super.findById(id);

        note = this.loadForeignField(note);
        return note;
    }

    @Override
    public List<Note> findAll() {
//        QueryWrapper query = QueryWrapper.create()
//                .select()
//                .orderBy("create_at desc");
//
//        List<Note> notes =  this.mapper.selectListByQuery(query);

        List<Note> notes = this.mapper.selectListByQuery(
                QueryWrapper.create().orderBy("create_at desc")
        );

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

    @Override
    public List<Note> findByNotesBySiteId(Long siteId, LocalDateTime begin) {
//        QueryWrapper query = new QueryWrapper();
//        query.eq("site_id", siteId)
//                .lt("upgrade_at", begin)
//                .orderByDesc("upgrade_at")
//                .last("limit 50");
//
//        List<Note> originNotes =  this.mapper.selectList(query);

        List<Note> originNotes = this.mapper.selectListByQuery(
          QueryWrapper.create()
                  .lt("upgrade_at", begin)
                  .eq("site_id", siteId)
                  .orderBy("upgrade_at desc")
                  .limit(50)
        );

        List<Note> notes = this.loadForeign(originNotes);

        return notes;
    }

}
