package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.PermissionTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:13
 * @Version 1.0
 */
@Getter
@Setter
public class CmsPermissionDto extends BaseDto<Integer> {
    private Integer parentId;
    private Integer type;
    private String icon;
    private String name;
    private String action;
    private String url;
    private Integer priority;

    private List<CmsPermissionDto> children;
}
