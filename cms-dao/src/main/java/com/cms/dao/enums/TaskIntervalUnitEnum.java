package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @Author jayy
 * @Description 定时任务间隔单位
 * @Date 2020/11/27 15:53
 * @Version 1.0
 */
@Getter
public enum TaskIntervalUnitEnum implements BaseEnum {

    MINUTE(0,"分钟"),
    HOUR(1,"小时"),
    DAY(2,"每天"),
    WEEK(3,"每周"),
    MONTH(4,"每月");

    private int ordinal;
    private String label;

    TaskIntervalUnitEnum(int ordinal,String label){
        this.ordinal = ordinal;
        this.label = label;
    }
}
