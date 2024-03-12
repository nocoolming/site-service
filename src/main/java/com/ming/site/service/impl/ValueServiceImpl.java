package com.ming.site.service.impl;

import com.ming.site.mapper.ValueMapper;
import com.ming.site.model.Option;
import com.ming.site.model.Value;
import com.ming.site.model.Variant;
import com.ming.site.model.VariantValue;
import com.ming.site.service.*;
import com.ming.site.util.SnowflakeUtil;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ValueServiceImpl
        extends AbstractService<Value, Long, ValueMapper>
        implements ValueService {
    private static final Logger log = LoggerFactory.getLogger(ValueServiceImpl.class);

    @Autowired
    VariantService variantService;
    @Autowired
    VariantValueService variantValueService;
    @Autowired
    OptionService optionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Value insert(Value value) {
        value.setId(SnowflakeUtil.nextId());
        super.insert(value);

        List<Option> options = optionService.getOptionsByProductId(value.getProductId());

        // 如果有option下没有value，则不生成variants, 直接返回 value
        for (var option : options) {
            if (option.getValues() == null || option.getValues().isEmpty()) {
                return value;
            }
        }


        return value;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteById(Long id) {
        variantValueService.removeVariantValueByValueId(id);
        return super.deleteById(id);
    }

    @Override
    public void removeValuesByProductId(long productId) {
        this.mapper.deleteByQuery(
                QueryWrapper.create()
                        .where("product_id=?", productId)
        );
    }
}

