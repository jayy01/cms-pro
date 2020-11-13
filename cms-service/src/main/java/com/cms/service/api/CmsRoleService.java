package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsRoleDto;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:38
 * @Version 1.0
 */
public interface CmsRoleService extends BaseService<CmsRoleDto,Integer> {
    /**
     * 获取全部数据
     * @return
     */
    List<CmsRoleDto> getList(CmsRoleDto cmsRoleDto);

}
