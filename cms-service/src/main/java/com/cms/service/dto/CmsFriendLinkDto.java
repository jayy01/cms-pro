package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 17:57
 * @Version 1.0
 */
@Getter
@Setter
public class CmsFriendLinkDto extends BaseDto<Integer> {
    private String name;
    private String url;
    private Integer priority;
}
