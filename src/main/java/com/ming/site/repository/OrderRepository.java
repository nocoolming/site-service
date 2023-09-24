package com.ming.site.repository;

import com.ming.site.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository
        extends PagingAndSortingRepository<Order, Long>, CrudRepository<Order, Long> {
}
