package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Comment;
import com.ming.site.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/file")
public class CommentControllerV1
        extends AbstractControllerV1<Comment, Long, CommentService> {
    private static final Logger log = LoggerFactory.getLogger(CommentControllerV1.class);
}
