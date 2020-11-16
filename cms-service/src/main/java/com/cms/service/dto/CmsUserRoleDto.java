package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 9:31
 * @Version 1.0
 */
@Getter
@Setter
public class CmsUserRoleDto extends BaseDto<Integer> {

    private Integer userId;
    private Integer roleId;

}
