package com.ming.site.service.impl;

import com.ming.site.mapper.OptionMapper;
import com.ming.site.model.Option;
import com.ming.site.service.AbstractService;
import com.ming.site.service.OptionService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl
        extends AbstractService<Option, Long, OptionMapper>
        implements OptionService {
    private static final Logger log = LoggerFactory.getLogger(OptionServiceImpl.class);

    @Autowired
    OptionMapper optionMapper;

    @Override
    public List<Option> getOptionsByProductId(long productId) {
        List<Option> list = optionMapper.selectListByQuery(
                QueryWrapper.create().where(
                        "product_id=?", productId
                )
        );
        return list;
    }
}
