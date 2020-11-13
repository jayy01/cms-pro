package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.core.foundation.TreeDto;
import com.cms.dao.enums.PermissionTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:13
 * @Version 1.0
 */
@Getter
@Setter
public class CmsPermissionDto extends TreeDto<CmsPermissionDto,Integer> {
    private Integer parentId;
    private Integer type;
    private String icon;
    private String name;
    private String action;
    private String url;
    private Integer priority;
}
