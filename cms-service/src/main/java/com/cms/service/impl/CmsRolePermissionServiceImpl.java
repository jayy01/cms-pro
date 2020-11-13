package com.cms.service.impl;

import com.cms.core.foundation.BasePage;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRolePermissionService;
import com.cms.service.dto.CmsRolePermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:52
 * @Version 1.0
 */
@Service
public class CmsRolePermissionServiceImpl implements CmsRolePermissionService {

    @Autowired
    CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    public void save(CmsRolePermissionDto dto) {

    }

    @Override
    public void update(CmsRolePermissionDto dto) {

    }

    @Override
    public CmsRolePermissionDto getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public BasePage<CmsRolePermissionDto> getPage(CmsRolePermissionDto dto) {
        return null;
    }

    @Override
    public List<Integer> getPermissionByRoleId(Integer roleId) {
        return cmsRolePermissionMapper.getPermissionByRoleId(roleId);
    }
}
