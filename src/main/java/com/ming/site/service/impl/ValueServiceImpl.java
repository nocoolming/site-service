package com.ming.site.service.impl;

import com.ming.site.mapper.ValueMapper;
import com.ming.site.model.Option;
import com.ming.site.model.Value;
import com.ming.site.model.Variant;
import com.ming.site.model.VariantValue;
import com.ming.site.service.*;
import com.ming.site.util.SnowflakeUtil;
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
        extends AbstractService<
        Value,
        Long,
        ValueMapper>
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


        // 开始生成Variants

        return value;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void buildVariants(List<Option> options, long productId) {
        Map<Integer, List<Value>> map = new HashMap<>();
        long size = 0l;
        for (int i = 0; i < options.size(); i++) {
            map.put(i, options.get(i).getValues());

            size += options.get(i).getValues().size();
        }

        Map<Variant, List<VariantValue>> variantListMap = new ConcurrentHashMap<>();

        this.forEachValues(map, 0, 0, productId, null, variantListMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void forEachValues(
            Map<Integer, List<Value>> map,
            int deep,
            int index,
            long productId,
            Variant currentVariant,
            Map<Variant, List<VariantValue>> variantValueMap) {
        if (deep == map.size()) {
            return;
        }

        List<Value> values = map.get(deep);

        Variant variant = null;
        List<VariantValue> variantValueList = null;
        if (deep == 0) {
            variant = new Variant();
            variant.setId(SnowflakeUtil.nextId());
            variant.setProductId(productId);

//            variantService.insert(variant);

            variantValueList = new ArrayList<>();
            variantValueMap.put(variant, variantValueList);
        } else {
            variant = currentVariant;
            variantValueList = variantValueMap.get(variant);
        }

        Value currentValue = values.get(index);
        VariantValue variantValue = new VariantValue();

        variantValue.setValueId(currentValue.getId());
        variantValue.setVariantId(variant.getId());

//        variantValueService.insert(variantValue);
        variantValueList.add(variantValue);

        if (index == values.size()) {
            forEachValues(map, deep + 1, 0, productId, variant, variantValueMap);

            return;
        }

        if (deep < map.size() - 1) {
            forEachValues(map, deep, index + 1, productId, variant, variantValueMap);
        }


    }
}
