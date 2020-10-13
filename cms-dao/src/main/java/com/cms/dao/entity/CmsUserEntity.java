package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
    private Integer status;
}
