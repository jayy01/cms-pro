package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsProperties;
import com.cms.context.utils.UtilsShiro;
import com.cms.core.foundation.BasePage;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.mapper.CmsUserMapper;
import com.cms.dao.mapper.CmsUserRoleMapper;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserConverter;
import com.cms.service.converter.CmsUserRoleConverter;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserRoleDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

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
    @Autowired
    CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    UtilsProperties utilsProperties;

    @Override
    public CmsUserDto selectByUsername(String username) {
        CmsUserEntity cmsUserEntity = cmsUserMapper.getByUsername(username);
        CmsUserDto cmsUserDto = CmsUserConverter.CONVERTER.entityToDto(cmsUserEntity);
        return cmsUserDto;
    }

    @Override
    public CmsUserDto getByEmail(String email) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.selectByEmail(email));
    }

    @Override
    public BasePage<CmsUserDto> getPage(CmsUserDto cmsUserDto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsUserConverter.CONVERTER.dtoToEntity(cmsUserDto));
        Page<CmsUserEntity> page = PageHelper.startPage(pageInfo.getPagecurrent(),pageInfo.getPageSize()).doSelectPage(() -> {
            cmsUserMapper.selectAll(of);
        });
        return new  BasePage<CmsUserDto>(page.getTotal(),CmsUserConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public void updateLoginCount(Integer id) {
        cmsUserMapper.updateLoginCount(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CmsUserDto dto) {
        String ip = UtilsHttp.getRemoteAddress();
        String salt = UtilsShiro.getSalt();
        dto.setPassword(UtilsShiro.sha256(dto.getPassword(),salt,Integer.parseInt(utilsProperties.getPropertiesValue("shiro.hash.iterations"))));
        dto.setSalt(salt);
        dto.setRegisterIp(ip);
        dto.setRegisterTime(LocalDateTime.now());
        CmsUserEntity cmsUserEntity = CmsUserConverter.CONVERTER.dtoToEntity(dto);
        cmsUserMapper.save(cmsUserEntity);
        if(Objects.nonNull(dto.getRoleId())){
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setUserId(cmsUserEntity.getId());
            cmsUserRoleDto.setRoleId(dto.getRoleId());
            cmsUserRoleMapper.save(CmsUserRoleConverter.CONVERTER.dtoToEntity(cmsUserRoleDto));
        }
    }

    @Override
    public void update(CmsUserDto dto) {
        // 更新时密码有值的时候
        if(Objects.nonNull(dto.getPassword())){
            String salt = UtilsShiro.getSalt();
            dto.setPassword(UtilsShiro.sha256(dto.getPassword(),salt,Integer.parseInt(utilsProperties.getPropertiesValue("shiro.hash.iterations"))));
            dto.setSalt(salt);
        }
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(dto));
        if(Objects.nonNull(dto.getRoleId())){
            cmsUserRoleMapper.deleteByUserId(dto.getId());
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setUserId(dto.getId());
            cmsUserRoleDto.setRoleId(dto.getRoleId());
            cmsUserRoleMapper.save(CmsUserRoleConverter.CONVERTER.dtoToEntity(cmsUserRoleDto));
        }
    }

    @Override
    public CmsUserDto getById(Integer id) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.selectById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        cmsUserMapper.deleteById(id);
        cmsUserRoleMapper.deleteByUserId(id);
    }

}
