package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:47
 * @Version 1.0
 */
@Getter
@Setter
public class CmsRolePermissionDto extends BaseDto<Integer> {

    private Integer roleId;
    private Integer permissionId;

}
