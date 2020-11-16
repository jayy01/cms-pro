package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsUserEntity;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 16:51
 * @Version 1.0
 */
public interface CmsUserMapper extends BaseMapper<CmsUserEntity,Integer> {
    /**
     * 根据名称查询
     * @param username
     * @return
     */
    CmsUserEntity getByUsername(String username);

    /**
     * 按条件获取全部数据
     * @param searchProvider  条件
     * @return
     */
    List<CmsUserEntity> selectAll(SearchProvider searchProvider);
}
