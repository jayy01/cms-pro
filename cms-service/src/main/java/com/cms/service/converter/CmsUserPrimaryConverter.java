package com.cms.service.converter;

import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/30 9:44
 * @Version 1.0
 */
@Mapper
public interface CmsUserPrimaryConverter {

    CmsUserPrimaryConverter CONVERTER = Mappers.getMapper(CmsUserPrimaryConverter.class);

    CmsUserPrimaryDto entutyToDto(CmsUserPrimaryEntity entity);

}
