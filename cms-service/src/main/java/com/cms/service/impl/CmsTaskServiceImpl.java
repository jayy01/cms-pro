package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.core.foundation.BasePage;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.mapper.CmsTaskMapper;
import com.cms.service.api.CmsTaskService;
import com.cms.service.converter.CmsTaskConverter;
import com.cms.service.dto.CmsTaskDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/27 16:21
 * @Version 1.0
 */
@Service
public class CmsTaskServiceImpl implements CmsTaskService {

    @Autowired
    CmsTaskMapper cmsTaskMapper;

    @Override
    public void save(CmsTaskDto dto) {
        cmsTaskMapper.save(CmsTaskConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsTaskDto dto) {
        cmsTaskMapper.update(CmsTaskConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsTaskDto getById(Integer id) {
        return CmsTaskConverter.CONVERTER.entityToDto(cmsTaskMapper.selectById(id));
    }

    @Override
    public void deleteById(Integer id) {
        cmsTaskMapper.deleteById(id);
    }

    @Override
    public BasePage<CmsTaskDto> getPage(CmsTaskDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        Page<CmsTaskEntity> page = PageHelper.startPage(pageInfo.getPagecurrent(), pageInfo.getPageSize()).doSelectPage(() -> {
            cmsTaskMapper.selectBySearchProvider(SearchProvider.of(CmsTaskConverter.CONVERTER.dtoToEntity(dto)));
        });
        return new  BasePage(page.getTotal(),page.getResult());
    }
}
