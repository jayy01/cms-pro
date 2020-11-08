package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsPermissionEntity;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:16
 * @Version 1.0
 */
public interface CmsPermissionMapper extends BaseMapper<CmsPermissionEntity,Integer> {

    List<CmsPermissionEntity> selectAll();

    void deleteById(Integer id);

    List<CmsPermissionEntity> selectByParentId(Integer id);
}
