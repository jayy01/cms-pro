package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsFriendLinkDto;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 17:57
 * @Version 1.0
 */
public interface CmsFriendLinkService extends BaseService<CmsFriendLinkDto,Integer> {
    /**
     * 获取全部友情链接
     * @return
     */
    List<CmsFriendLinkDto> getList();

}
