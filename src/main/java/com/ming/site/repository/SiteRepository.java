package com.ming.site.repository;

import com.ming.site.model.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "productImage", path = "productImage")
public interface SiteRepository
        extends PagingAndSortingRepository<Site, Long>, CrudRepository<Site, Long> {
    Optional<Site> findByDomain(String domain);
}
