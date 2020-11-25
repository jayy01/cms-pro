package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/25 14:57
 * @Version 1.0
 */
@Getter
public enum StaticSuffixEnum implements BaseEnum {

    SHTML(0,"shtml"),
    FTL(1,"ftl");

    private int ordinal;
    private String label;

    StaticSuffixEnum(int ordinal,String label){
        this.ordinal = ordinal;
        this.label = label;
    }

}
