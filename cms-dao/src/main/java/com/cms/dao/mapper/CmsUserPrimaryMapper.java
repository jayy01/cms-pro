package com.cms.dao.mapper;

import com.cms.dao.entity.CmsUserPrimaryEntity;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 16:51
 * @Version 1.0
 */
public interface CmsUserPrimaryMapper {
    /**
     * 根据名称查询
     * @param username
     * @return
     */
    CmsUserPrimaryEntity getByUsername(String username);
}
