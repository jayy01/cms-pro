package com.cms.service.converter;

import com.cms.dao.entity.CmsFriendLinkEntity;
import com.cms.service.dto.CmsFriendLinkDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 17:59
 * @Version 1.0
 */
@Mapper
public interface CmsFriendLinkConverter {
    CmsFriendLinkConverter CONVERTER = Mappers.getMapper(CmsFriendLinkConverter.class);

    CmsFriendLinkDto entityToDto(CmsFriendLinkEntity entity);

    CmsFriendLinkEntity dtoToEntity(CmsFriendLinkDto dto);

    List<CmsFriendLinkDto> entityToDto(List<CmsFriendLinkEntity> entities);
}
