package com.ming.site.repository;

import com.ming.site.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository
        extends CrudRepository<Comment, Long> {
}
