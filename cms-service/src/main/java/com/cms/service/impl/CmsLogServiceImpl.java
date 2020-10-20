package com.cms.service.impl;

import com.cms.dao.mapper.CmsLogMapper;
import com.cms.service.api.CmsLogService;
import com.cms.service.converter.CmsLogConverter;
import com.cms.service.dto.CmsLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/13 16:22
 * @Version 1.0
 */
@Service
public class CmsLogServiceImpl implements CmsLogService {

    @Autowired
    CmsLogMapper cmsLogMapper;

    @Override
    public void save(CmsLogDto dto) {
        cmsLogMapper.save(CmsLogConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsLogDto dto) {

    }

    @Override
    public CmsLogDto getById(Integer id) {
        return null;
    }
}
