package com.cms.service.impl;

import com.cms.core.foundation.BasePage;
import com.cms.dao.mapper.CmsUserRoleMapper;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.dto.CmsUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 9:33
 * @Version 1.0
 */
@Service
public class CmsUserRoleServiceImpl implements CmsUserRoleService {

    @Autowired
    CmsUserRoleMapper cmsUserRoleMapper;

    @Override
    public void save(CmsUserRoleDto dto) {

    }

    @Override
    public void update(CmsUserRoleDto dto) {

    }

    @Override
    public CmsUserRoleDto getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public BasePage<CmsUserRoleDto> getPage(CmsUserRoleDto dto) {
        return null;
    }


    @Override
    public Integer getByUserId(Integer userId) {
        return cmsUserRoleMapper.getByUserId(userId);
    }
}

