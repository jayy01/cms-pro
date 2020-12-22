package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsTaskEntity;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/27 15:26
 * @Version 1.0
 */
public interface CmsTaskMapper extends BaseMapper<CmsTaskEntity,Integer> {

    /**
     * 分页查询
     * @param searchProvider
     * @return
     */
    List<SearchProvider> selectBySearchProvider(SearchProvider<CmsTaskEntity> searchProvider);
}
