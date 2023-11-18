package com.ming.site.service.impl;

import com.ming.site.mapper.AddressMapper;
import com.ming.site.model.Address;
import com.ming.site.service.AbstractService;
import com.ming.site.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends AbstractService<Address, Long, AddressMapper> implements AddressService {
    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

}
