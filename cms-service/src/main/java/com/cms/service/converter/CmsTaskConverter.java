package com.cms.service.converter;

import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsTaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/27 15:40
 * @Version 1.0
 */
@Mapper(uses = EnumConverter.class)
public interface CmsTaskConverter {

    CmsTaskConverter CONVERTER = Mappers.getMapper(CmsTaskConverter.class);

    CmsTaskDto entityToDto(CmsTaskEntity entity);

    CmsTaskEntity dtoToEntity(CmsTaskDto dto);

    List<CmsTaskDto> entityToDto(List<CmsTaskEntity> entities);

}
