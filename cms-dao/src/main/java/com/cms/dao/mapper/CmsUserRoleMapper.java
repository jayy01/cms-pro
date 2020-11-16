package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.entity.CmsUserRoleEntity;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 9:22
 * @Version 1.0
 */
public interface CmsUserRoleMapper extends BaseMapper<CmsUserRoleEntity,Integer> {
    /**
     * 根据用户id删除
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 根据用户id查找
     * @param userId
     * @return
     */
    Integer getByUserId(Integer userId);


}
