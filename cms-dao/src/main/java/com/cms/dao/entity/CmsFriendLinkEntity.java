package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/16 17:53
 * @Version 1.0
 */
@Getter
@Setter
public class CmsFriendLinkEntity extends BaseEntity<Integer> {
    private String name;
    private String url;
    private Integer priority;
}
