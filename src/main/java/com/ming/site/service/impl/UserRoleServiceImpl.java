package com.ming.site.service.impl;

import com.ming.site.mapper.UserRoleMapper;
import com.ming.site.model.UserRole;
import com.ming.site.service.AbstractRelationShipService;
import com.ming.site.service.AbstractService;
import com.ming.site.service.UserRoleService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleServiceImpl extends AbstractRelationShipService<UserRole, UserRole> implements UserRoleService {
    private static final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserRole insert(UserRole o) {
        if (this.existsById(o)) {
            log.debug("已存在");
            return null;
        }

        return super.insert(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(UserRole userRole) {
        QueryWrapper query = QueryWrapper.create()
                .where("user_id=? ", userRole.getUserId())
                .and("role_id=?", userRole.getRoleId());

        mapper.deleteByQuery(query);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean existsById(UserRole userRole) {
        QueryWrapper query = QueryWrapper.create()
                .where("user_id=? ", userRole.getUserId())
                .and("role_id=?", userRole.getRoleId());
        List<UserRole> list = mapper.selectListByQuery(query);

        return list.size() > 0;
    }
}
