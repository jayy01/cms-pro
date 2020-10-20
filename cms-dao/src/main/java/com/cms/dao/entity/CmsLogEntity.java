package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description 日志实体类
 * @Date 2020/10/13 14:56
 * @Version 1.0
 */
@Getter
@Setter
public class CmsLogEntity extends BaseEntity<Integer> {

    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;

}
