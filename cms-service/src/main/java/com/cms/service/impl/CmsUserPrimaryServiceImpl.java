package com.cms.service.impl;

import com.cms.core.foundation.BasePage;
import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.dao.mapper.CmsUserPrimaryMapper;
import com.cms.service.api.CmsUserPrimartService;
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
public class CmsUserPrimaryServiceImpl implements CmsUserPrimartService {

    @Autowired
    CmsUserPrimaryMapper cmsUserPrimaryMapper;


    @Override
    public void save(CmsUserPrimaryDto dto) {

    }

    @Override
    public void update(CmsUserPrimaryDto dto) {

    }

    @Override
    public CmsUserPrimaryDto getById(Integer id) {
        return CmsUserPrimaryConverter.CONVERTER.entutyToDto(cmsUserPrimaryMapper.selectById(id));
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public BasePage<CmsUserPrimaryDto> getPage(CmsUserPrimaryDto dto) {
        return null;
    }
}
