package com.cms.core.enums;

import com.cms.core.foundation.BaseEnum;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/3 13:28
 * @Version 1.0
 */
public enum RestCodeEnum implements BaseEnum {
    /**
     * 错误码
     */
    ERROR(500,"操作失败！");


    private Integer ordinal;
    private String label;

    RestCodeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
