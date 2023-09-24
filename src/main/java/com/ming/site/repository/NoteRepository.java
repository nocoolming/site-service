package com.ming.site.repository;

import com.ming.site.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository
        extends PagingAndSortingRepository<Note, Long>, CrudRepository<Note, Long> {
}
