package com.ming.site.repository;

import com.ming.site.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository
        extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
}
