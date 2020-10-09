package com.cms.service.impl;

import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.dao.mapper.CmsUserPrimaryMapper;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserPrimaryConverter;
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
    CmsUserPrimaryMapper cmsUserPrimaryMapper;

    @Override
    public CmsUserPrimaryDto selectByUsername(String username) {
        CmsUserPrimaryEntity cmsUserPrimaryEntity = cmsUserPrimaryMapper.getByUsername(username);
        CmsUserPrimaryDto cmsUserPrimaryDto = CmsUserPrimaryConverter.CONVERTER.entutyToDto(cmsUserPrimaryEntity);
        return cmsUserPrimaryDto;
    }
}
