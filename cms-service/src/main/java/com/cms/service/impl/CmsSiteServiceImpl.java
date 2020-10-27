package com.cms.service.impl;

import com.cms.dao.mapper.CmsSiteMapper;
import com.cms.service.api.CmsSiteService;
import com.cms.service.converter.CmsSiteConverter;
import com.cms.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/26 13:33
 * @Version 1.0
 */
@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    CmsSiteMapper cmsSiteMapper;

    @Override
    public void save(CmsSiteDto dto) {

    }

    @Override
    public void update(CmsSiteDto dto) {
        cmsSiteMapper.update(CmsSiteConverter.CONVERTER.dtoToentity(dto));
    }

    @Override
    public CmsSiteDto getById(Integer id) {
        return CmsSiteConverter.CONVERTER.entityToDto(cmsSiteMapper.selectById(id));
    }
}
