package com.ming.site.repository;

import com.ming.site.model.CategoryLanguage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categoryLanguage", path = "categoryLanguage")
public interface CategoryLanguageRepository
        extends PagingAndSortingRepository<CategoryLanguage, Long>, CrudRepository<CategoryLanguage, Long> {
}
