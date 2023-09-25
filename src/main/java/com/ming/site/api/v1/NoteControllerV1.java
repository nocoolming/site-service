package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Note;
import com.ming.site.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/note")
public class NoteControllerV1
extends AbstractControllerV1<
        Note,
        Long,
        NoteService> {
    private static final Logger log = LoggerFactory.getLogger(NoteControllerV1.class);
}
