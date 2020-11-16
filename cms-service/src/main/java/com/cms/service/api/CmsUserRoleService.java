package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserRoleDto;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 9:32
 * @Version 1.0
 */
public interface CmsUserRoleService extends BaseService<CmsUserRoleDto,Integer> {
    /**
     * 根据userId查询roleId
     * @param userId
     * @return
     */
    Integer getByUserId(Integer userId);

}
