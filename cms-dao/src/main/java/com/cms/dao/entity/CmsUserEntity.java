package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 9:27
 * @Version 1.0
 */
@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
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
}
