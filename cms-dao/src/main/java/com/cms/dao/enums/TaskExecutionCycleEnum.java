package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @Author jayy
 * @Description 定时任务执行方式
 * @Date 2020/11/27 15:49
 * @Version 1.0
 */
@Getter
public enum TaskExecutionCycleEnum implements BaseEnum {

    NO_EXPRESSION(0,"执行周期"),
    CORN_EXPRESSION(1,"执行方式");

    private int ordinal;
    private String label;

    TaskExecutionCycleEnum(int ordinal,String label){
        this.ordinal = ordinal;
        this.label = label;
    }
}
