package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsRolePermissionDto;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:51
 * @Version 1.0
 */
public interface CmsRolePermissionService extends BaseService<CmsRolePermissionDto,Integer> {
    List<Integer> getPermissionByRoleId(Integer roleId);
}
