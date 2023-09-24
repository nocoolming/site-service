package com.ming.site.repository;

import com.ming.site.model.PaymentOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "paymentOrder", path = "paymentOrder")
public interface PaymentOrderRepository
        extends PagingAndSortingRepository<PaymentOrder, Long>, CrudRepository<PaymentOrder, Long> {
}
