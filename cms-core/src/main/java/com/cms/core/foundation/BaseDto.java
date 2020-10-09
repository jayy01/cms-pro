package com.cms.core.foundation;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/30 11:26
 * @Version 1.0
 */
public class BaseDto<PK extends Serializable> implements Serializable {

    private PK id;
    private LocalTime createTime;
    private LocalTime updateTime;


    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public LocalTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalTime createTime) {
        this.createTime = createTime;
    }

    public LocalTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalTime updateTime) {
        this.updateTime = updateTime;
    }
}
