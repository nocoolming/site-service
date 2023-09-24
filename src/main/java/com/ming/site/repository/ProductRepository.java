package com.ming.site.repository;

import com.ming.site.model.Permission;
import com.ming.site.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository
        extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long> {
}
