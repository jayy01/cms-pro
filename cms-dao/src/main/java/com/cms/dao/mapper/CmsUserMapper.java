package com.cms.dao.mapper;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.entity.CmsUserPrimaryEntity;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 16:51
 * @Version 1.0
 */
public interface CmsUserMapper {
    /**
     * 根据名称查询
     * @param username
     * @return
     */
    CmsUserEntity getByUsername(String username);
}
