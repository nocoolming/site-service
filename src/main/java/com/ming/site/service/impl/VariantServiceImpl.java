package com.ming.site.service.impl;

import com.ming.site.mapper.VariantMapper;
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

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VariantServiceImpl extends AbstractService<Variant, Long, VariantMapper> implements VariantService {
    private static final Logger log = LoggerFactory.getLogger(VariantServiceImpl.class);

    @Autowired
    ValueService valueService;
    @Autowired
    VariantValueService variantValueService;

    @Autowired
    OptionService optionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeVariantsAndVariantValuesByProductId(long productId) {
        variantValueService.removeVariantValueByProductId(productId);

        this.mapper.deleteByQuery(QueryWrapper.create().where("product_id=?", productId));


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Variant> getVariantsByProductId(long productId) {
        List<Variant> list =
                this.mapper.selectListWithRelationsByQuery(
                        QueryWrapper.create()
                                .where("product_id=?", productId));

        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Variant> buildVariants(long productId) throws NotAllOptionsHaveValuesException {
        this.removeVariantsAndVariantValuesByProductId(productId);

        List<Option> options = optionService.getOptionsByProductId(productId);
        Map<Integer, List<Value>> map = new HashMap<>();

        for (int i = 0; i < options.size(); i++) {
            List<Value> values = options.get(i).getValues();

//            if (values == null || values.isEmpty()) {
//                throw new NotAllOptionsHaveValuesException();
//            }

            map.put(i, values);
        }

        Map<Variant, List<VariantValue>> resultMap = new ConcurrentHashMap<>();

        this.forEachValues(map, 0, 0, productId, null, resultMap);

        for (Variant variant : resultMap.keySet()) {
            mapper.insert(variant);

            List<VariantValue> list = resultMap.get(variant);

            for (VariantValue variantValue : list) {
                variantValueService.insert(variantValue);
            }
        }


        return this.getVariantsByProductId(productId);
    }

    public void forEachValues(
            Map<Integer, List<Value>> map,
            int deep, int index, long productId,
            Map<Integer, Value> valuesMap,
            Map<Variant, List<VariantValue>> resultMap) {
        if (deep >= map.size()) {
            // 数组越界
            return;
        }

        if (deep == 0) {
            // deep == 0 时，new一个新的values
            valuesMap = new HashMap<>();
        }

        List<Value> layoutValues = map.get(deep);

        if (index >= layoutValues.size()) {
//            forEachValues(map, deep + 1, 0, productId, values);
            return;
        }
        // 当前的value加入values
        Value currentValue = layoutValues.get(index);

        try {
            valuesMap.put(deep, currentValue);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (deep == map.size() - 1) {
            // 到达最底层,
            // 这一层的业务处理，写到外面的函数，与递归分开。
            // 这块业务不复杂，但是多，写在一起，可读性就差了
            buildVariantValues(valuesMap, productId, resultMap);


            forEachValues(map, deep, index + 1, productId, valuesMap, resultMap);
        } else {
            // 这里有两个情况 ，一个是当前层index + 1, 一个是index到顶，deep +1
            if (index == valuesMap.size()) {
                // 当index到底时， 只能递归下一级深度
                forEachValues(map, deep + 1, 0, productId, valuesMap, resultMap);
            } else {

                forEachValues(map, deep + 1, 0, productId, valuesMap, resultMap);
                forEachValues(map, deep, index + 1, productId, valuesMap, resultMap);
            }
        }


    }

    void buildVariantValues(Map<Integer, Value> valuesMap, long productId, Map<Variant, List<VariantValue>> resultMap) {
        Variant variant = new Variant();
        variant.setId(SnowflakeUtil.nextId());
        variant.setProductId(productId);
        variant.setPrice(BigDecimal.ZERO);
        variant.setQuantity(0);

//        this.insert(variant);
        List<VariantValue> list = new ArrayList<>();
        // values是前面几级的values，遍历构造VariantValue instance并加入到variantValueList
        for (int index : valuesMap.keySet()) {
            Value value = valuesMap.get(index);
            VariantValue vv = new VariantValue();
            vv.setVariantId(variant.getId());
            vv.setValueId(value.getId());
            vv.setValue(value);
//            variantValueService.insert(vv);

            list.add(vv);
        }

        resultMap.put(variant, list);
    }
}
