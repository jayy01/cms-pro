package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserPrimaryDto;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/30 9:30
 * @Version 1.0
 */
public interface CmsUserService extends BaseService<CmsUserDto,Integer> {
    /**
     * 根据用户名称查询
     * @param username
     * @return
     */
    CmsUserDto selectByUsername(String username);

}
