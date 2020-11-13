package com.cms.service.converter;

import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.entity.CmsRolePromissionEntity;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.dto.CmsRolePermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:48
 * @Version 1.0
 */
@Mapper
public interface CmsRolePermissionConverter {

    CmsRolePermissionConverter CONVERTER = Mappers.getMapper(CmsRolePermissionConverter.class);

    CmsRolePromissionEntity dtoToEntity(CmsRolePermissionDto dto);

    CmsRolePermissionDto entityToDto(CmsRolePromissionEntity entity);

}
