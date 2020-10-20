package com.cms.service.converter;

import com.cms.dao.entity.CmsLogEntity;
import com.cms.service.dto.CmsLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/13 15:01
 * @Version 1.0
 */
@Mapper
public interface CmsLogConverter {

    CmsLogConverter CONVERTER = Mappers.getMapper(CmsLogConverter.class);

    CmsLogDto entityToDto(CmsLogEntity entity);

    CmsLogEntity dtoToEntity(CmsLogDto dto);

}
