package com.ming.site.service.impl;

import com.ming.site.mapper.VariantMapper;
import com.ming.site.model.Variant;
import com.ming.site.service.AbstractService;
import com.ming.site.service.VariantService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl
        extends AbstractService<Variant, Long, VariantMapper>
        implements VariantService {
    private static final Logger log = LoggerFactory.getLogger(VariantServiceImpl.class);

    @Autowired
    VariantMapper variantMapper;

    @Override
    public List<Variant> getVariantsByProductId(long productId) {
        List<Variant> list = variantMapper.selectListByQuery(
                QueryWrapper.create().where(
                        "product_id=?", productId
                )
        );
        return list;
    }
}
