package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 8:34
 * @Version 1.0
 */
@Getter
@Setter
public class CmsUserDto extends BaseDto<Integer> {

    private String username;
    private UserStatusEnum status;

    private Boolean admin;
    /**
     * 超级管理员
     */
    private Boolean administrator;
}
