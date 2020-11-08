package com.cms.service.converter;

import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.service.dto.CmsPermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:14
 * @Version 1.0
 */
@Mapper
public interface CmsPermissionConverter {
    CmsPermissionConverter CONVERTER = Mappers.getMapper(CmsPermissionConverter.class);

    CmsPermissionEntity dtoToEntity(CmsPermissionDto dto);

    CmsPermissionDto entityToDto(CmsPermissionEntity entity);

    List<CmsPermissionDto> entityToDto(List<CmsPermissionEntity> entities);
}
