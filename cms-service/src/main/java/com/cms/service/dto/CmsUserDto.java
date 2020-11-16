package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String password;
    private String salt;
    private String email;
    private Boolean backend;
    private Boolean supper;
    private LocalDateTime registerTime;
    private String registerIp;
    private Integer loginCount;
    private Boolean status;
    private Boolean delete;

    private Integer roleId;

}
