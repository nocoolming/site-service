package com.ming.site.repository;

import com.ming.site.model.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "permission", path = "permission")
public interface PermissionRepository
        extends PagingAndSortingRepository<Permission, Long>, CrudRepository<Permission, Long> {
}
