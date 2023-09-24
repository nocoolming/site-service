package com.ming.site.repository;

import com.ming.site.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orderDetail", path = "orderDetail")
public interface OrderDetailRepository
        extends PagingAndSortingRepository<OrderDetail, Long>, CrudRepository<OrderDetail, Long> {
}
