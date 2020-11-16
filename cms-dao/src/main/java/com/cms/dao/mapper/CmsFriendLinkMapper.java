package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsFriendLinkEntity;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 17:55
 * @Version 1.0
 */
public interface CmsFriendLinkMapper extends BaseMapper<CmsFriendLinkEntity,Integer> {
    /**
     * 查询
     * @return
     */
    List<CmsFriendLinkEntity> selectBySearchProvider(SearchProvider searchProvider);

}
