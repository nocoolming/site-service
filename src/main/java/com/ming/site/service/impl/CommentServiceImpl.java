package com.ming.site.service.impl;

import com.ming.site.mapper.CommentMapper;
import com.ming.site.model.Comment;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractService<Comment, Long, CommentMapper>
        implements CommentService {
}
