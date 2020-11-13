package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:27
 * @Version 1.0
 */
@Getter
@Setter
public class CmsRoleEntity extends BaseEntity<Integer> {

    private String name;
    private String priority;
    private Boolean all;
    private boolean status;
}
