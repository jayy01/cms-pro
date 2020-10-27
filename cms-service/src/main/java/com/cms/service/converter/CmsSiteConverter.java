package com.cms.service.converter;

import com.cms.dao.entity.CmsSiteEntity;
import com.cms.service.dto.CmsSiteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/26 13:56
 * @Version 1.0
 */
@Mapper
public interface CmsSiteConverter {

    CmsSiteConverter CONVERTER = Mappers.getMapper(CmsSiteConverter.class);

    CmsSiteDto entityToDto(CmsSiteEntity entity);

    CmsSiteEntity dtoToentity(CmsSiteDto dto);

}
