package com.cms.service.converter;

import com.cms.dao.entity.CmsUserRoleEntity;
import com.cms.service.dto.CmsUserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 9:44
 * @Version 1.0
 */
@Mapper
public interface CmsUserRoleConverter {

    CmsUserRoleConverter CONVERTER = Mappers.getMapper(CmsUserRoleConverter.class);

    CmsUserRoleEntity dtoToEntity(CmsUserRoleDto dto);

}
