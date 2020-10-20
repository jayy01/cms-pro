package com.cms.service.impl;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.dao.mapper.CmsUserMapper;
import com.cms.dao.mapper.CmsUserPrimaryMapper;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserConverter;
import com.cms.service.converter.CmsUserPrimaryConverter;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/30 9:30
 * @Version 1.0
 */
@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    CmsUserMapper cmsUserMapper;

    @Override
    public CmsUserDto selectByUsername(String username) {
        CmsUserEntity cmsUserEntity = cmsUserMapper.getByUsername(username);

        CmsUserDto cmsUserDto = CmsUserConverter.CONVERTER.entityToDto(cmsUserEntity);

        return cmsUserDto;
    }

    @Override
    public void save(CmsUserDto dto) {

    }

    @Override
    public void update(CmsUserDto dto) {
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsUserDto getById(Integer id) {
        return null;
    }
}
