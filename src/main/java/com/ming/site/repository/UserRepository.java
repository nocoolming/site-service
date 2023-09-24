package com.ming.site.repository;

import com.ming.site.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository
        extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {
}
