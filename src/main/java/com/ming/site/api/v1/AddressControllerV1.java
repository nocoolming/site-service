package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Address;
import com.ming.site.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/address")
public class AddressControllerV1
    extends AbstractControllerV1<
        Address,
        Long,
        AddressService> {
    private static final Logger log = LoggerFactory.getLogger(AddressControllerV1.class);



}
