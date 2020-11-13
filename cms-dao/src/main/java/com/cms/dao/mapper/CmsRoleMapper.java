package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsRoleEntity;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:31
 * @Version 1.0
 */
public interface CmsRoleMapper extends BaseMapper<CmsRoleEntity,Integer> {
    /**
     * 查询数据
     * @param cmsRoleEntity
     * @return
     */
    List<CmsRoleEntity> select(CmsRoleEntity cmsRoleEntity);

    /**
     * 分页查询
     * @param searchProvider
     * @return
     */
    List<CmsRoleEntity> selectByPage(SearchProvider searchProvider);

}
