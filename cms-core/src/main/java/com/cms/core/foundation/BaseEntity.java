package com.cms.core.foundation;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 9:24
 * @Version 1.0
 */
public class BaseEntity<PK extends Serializable> implements Serializable {

    private PK id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}
