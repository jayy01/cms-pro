package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 9:21
 * @Version 1.0
 */
@Getter
@Setter
public class CmsUserRoleEntity extends BaseEntity<Integer> {

    private Integer userId;
    private Integer roleId;

}
