package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:11
 * @Version 1.0
 */
@Getter
@Setter
public class CmsPermissionEntity extends BaseEntity<Integer> {

    private Integer parentId;
    private Integer type;
    private String icon;
    private String name;
    private String action;
    private String url;
    private Integer priority;

}
