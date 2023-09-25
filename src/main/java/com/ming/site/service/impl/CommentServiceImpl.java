package com.ming.site.service.impl;

import com.ming.site.model.Comment;
import com.ming.site.repository.CommentRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractService<Comment, Long, CommentRepository>
        implements CommentService {
}
