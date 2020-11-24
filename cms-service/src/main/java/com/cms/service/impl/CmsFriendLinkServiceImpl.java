package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.core.foundation.BasePage;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsFriendLinkEntity;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.mapper.CmsFriendLinkMapper;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.converter.CmsFriendLinkConverter;
import com.cms.service.dto.CmsFriendLinkDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 17:58
 * @Version 1.0
 */
@Service
public class CmsFriendLinkServiceImpl implements CmsFriendLinkService {

    @Autowired
    CmsFriendLinkMapper cmsFriendLinkMapper;

    @Override
    public void save(CmsFriendLinkDto dto) {
        cmsFriendLinkMapper.save(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsFriendLinkDto dto) {
        cmsFriendLinkMapper.update(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsFriendLinkDto getById(Integer id) {
        return CmsFriendLinkConverter.CONVERTER.entityToDto(cmsFriendLinkMapper.selectById(id));
    }

    @Override
    public void deleteById(Integer id) {
        cmsFriendLinkMapper.deleteById(id);
    }

    @Override
    public BasePage<CmsFriendLinkDto> getPage(CmsFriendLinkDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
        Page<CmsFriendLinkEntity> page = PageHelper.startPage(pageInfo.getPagecurrent(), pageInfo.getPageSize()).doSelectPage(() -> {
            cmsFriendLinkMapper.selectBySearchProvider(of);
        });

        return new BasePage<CmsFriendLinkDto>(page.getTotal(),CmsFriendLinkConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public List<CmsFriendLinkDto> getList() {
        return CmsFriendLinkConverter.CONVERTER.entityToDto(cmsFriendLinkMapper.selectBySearchProvider(SearchProvider.of(new CmsFriendLinkEntity())));
    }
}
