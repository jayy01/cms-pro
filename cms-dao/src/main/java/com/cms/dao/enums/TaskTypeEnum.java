package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @Author jayy
 * @Description 定时任务类型
 * @Date 2020/11/27 15:44
 * @Version 1.0
 */
@Getter
public enum TaskTypeEnum implements BaseEnum {

    INDEX_STATIC(0,"首页静态化"),
    COLUMN_STATIC(1,"栏目静态化"),
    CONTENT_STATIC(2,"内容静态化");

    private int ordinal;
    private String label;

    TaskTypeEnum(int ordinal,String label){
         this.ordinal = ordinal;
         this.label = label;
    }

}
