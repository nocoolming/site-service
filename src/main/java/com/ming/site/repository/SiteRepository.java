package com.ming.site.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.site.model.Site;

import java.util.Optional;

public interface SiteRepository
        extends BaseMapper<Site> {
    Optional<Site> findByDomain(String domain);
}
