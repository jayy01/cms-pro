package com.cms.service.impl;

import com.cms.core.exception.BusinessException;
import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.dao.mapper.CmsPermissionMapper;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.converter.CmsPermissionConverter;
import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:24
 * @Version 1.0
 */
@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    CmsPermissionMapper cmsPermissionMapper;

    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsPermissionDto dto) {
        cmsPermissionMapper.update(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsPermissionDto getById(Integer id) {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectById(id));
    }

    @Override
    public List<CmsPermissionDto> selectTreeData(Integer excludeId) {

        List<CmsPermissionEntity> cmsPermissionEntitys = cmsPermissionMapper.selectAll();
        // 存放所有数据
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        // 存放parentId=0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        // 循环数据  进行处理
        cmsPermissionEntitys.forEach(x -> {
            CmsPermissionDto dto = CmsPermissionConverter.CONVERTER.entityToDto(x);
            Integer id = dto.getId();
            // 当id与传入id相等的时候排除 不插入
            if(Objects.nonNull(excludeId) && Objects.nonNull(excludeId) && id.compareTo(excludeId) == 0){
                return;
            }
            permissionMap.put(id,dto);
            // 获取parentId
            Integer parentId = x.getParentId();
            // 判断是否为顶级菜单
            if (parentId == 0){
                permissionList.add(dto);
            }else {
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                // 找不到父类  并且parentId与排除id相等
                if(Objects.isNull(cmsPermissionDto) && parentId.compareTo(excludeId) == 0){
                    return;
                }
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if(CollectionUtils.isEmpty(children)){
                    children = Lists.newArrayList();
                }
                children.add(dto);
                cmsPermissionDto.setChildren(children);
            }
        });
        // 默认从小到大排序
        //permissionList.sort(Comparator.comparing(CmsPermissionDto::getPriority));
        return permissionList;
    }

    @Override
    public List<CmsPermissionDto> getList() {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectAll());
    }

    @Override
    public void deleteById(Integer id) {
        List<CmsPermissionEntity> cmsPermissionEntities = cmsPermissionMapper.selectByParentId(id);
        if(!CollectionUtils.isEmpty(cmsPermissionEntities)){
            throw new BusinessException("改权限含有子权限，不能删除！");
        }
        cmsPermissionMapper.deleteById(id);
    }
}
