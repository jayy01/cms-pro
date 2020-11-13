package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsPermissionDto;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:23
 * @Version 1.0
 */
public interface CmsPermissionService extends BaseService<CmsPermissionDto,Integer> {
    /**
     * 获取树形格式化后数据
     * Integer id 排除id
     * @return
     */
    List<CmsPermissionDto>  getTreeData(Integer id);

    /**
     * 获取全部是数据
     * @return
     */
    List<CmsPermissionDto> getList();

}
