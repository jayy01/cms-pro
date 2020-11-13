package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:44
 * @Version 1.0
 */
@Getter
@Setter
public class CmsRolePromissionEntity extends BaseEntity<Integer> {

    private Integer roleId;
    private Integer permissionId;

}
