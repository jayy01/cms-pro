package com.cms.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 16:50
 * @Version 1.0
 */
@Getter
@Setter
public class CmsUserPrimaryEntity implements Serializable {

    private LocalDate createTime;
    private LocalDate updateTime;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
    private Boolean status;
    private Boolean deleted;

}
