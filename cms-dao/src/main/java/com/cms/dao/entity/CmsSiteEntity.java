package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/26 13:34
 * @Version 1.0
 */
@Getter
@Setter
public class CmsSiteEntity extends BaseEntity<Integer> {

    private String siteName;
    private String keywords;
    private String description;
    private String staticDir;
    private Integer staticSuffix;
    private Boolean staticIndex;

}
