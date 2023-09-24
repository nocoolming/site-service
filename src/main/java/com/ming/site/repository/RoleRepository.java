package com.ming.site.repository;

import com.ming.site.model.ProductImage;
import com.ming.site.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository
        extends PagingAndSortingRepository<Role, Long>, CrudRepository<Role, Long> {
}
