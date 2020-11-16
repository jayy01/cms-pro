package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.core.foundation.BasePage;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsRoleMapper;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRoleService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsRoleDto;
import com.cms.service.dto.CmsRolePermissionDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:39
 * @Version 1.0
 */
@Service
public class CmsRoleServiceImpl implements CmsRoleService {
    @Autowired
    CmsRoleMapper cmsRoleMapper;
    @Autowired
    CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class) // 开启事务  有任何错误 回滚
    public void save(CmsRoleDto dto) {
        CmsRoleEntity entity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        cmsRoleMapper.save(entity);
        // 批量保存勾选权限
        if (!dto.getAll()) {
            List<Integer> permission = dto.getPermission();
            if (!CollectionUtils.isEmpty(permission)) {
                cmsRolePermissionMapper.batchInsert(permission, entity.getId());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CmsRoleDto dto) {
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        // 更新角色表
        cmsRoleMapper.update(cmsRoleEntity);
        // 根据角色id删除角色权限中间便数据
        cmsRolePermissionMapper.deleteByRoleId(dto.getId());
        // 判断 是否拥有全部权限
        if(!dto.getAll()){
            List<Integer> permission = dto.getPermission();
            // 权限不为空时
            if(!CollectionUtils.isEmpty(permission)){
                // 插入角色权限中间表
                cmsRolePermissionMapper.batchInsert(permission,dto.getId());
            }
        }
    }

    @Override
    public CmsRoleDto getById(Integer id) {
        return CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.selectById(id));
    }

    @Override
    public List<CmsRoleDto> getList(CmsRoleDto cmsRoleDto) {
        return CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.select(CmsRoleConverter.CONVERTER.dtoToEntity(cmsRoleDto)));
    }

    @Override
    public List<String> getRoleActionsByUserId(Integer userId) {
        return cmsRoleMapper.selectRoleActionsByUserId(userId);
    }

    @Override
    public void deleteById(Integer id) {
        cmsRoleMapper.deleteById(id);
    }

    @Override
    public BasePage<CmsRoleDto> getPage(CmsRoleDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        Page<CmsRoleEntity> page = PageHelper.startPage(pageInfo.getPagecurrent(), pageInfo.getPageSize())
                .doSelectPage(() -> cmsRoleMapper.selectByPage(SearchProvider.of(CmsRoleConverter.CONVERTER.dtoToEntity(dto))));
        return new BasePage<CmsRoleDto>(page.getTotal(), CmsRoleConverter.CONVERTER.entityToDto(page.getResult()));
    }
}
