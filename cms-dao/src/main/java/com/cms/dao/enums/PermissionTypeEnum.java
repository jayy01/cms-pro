package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/28 10:10
 * @Version 1.0
 */
@Getter
public enum PermissionTypeEnum implements BaseEnum {

    MENU(1,"菜单"),
    BUTTON(0,"按钮");

    private int ordinal;
    private String label;

    PermissionTypeEnum(int ordinal,String label){
        this.ordinal = ordinal;
        this.label = label;
    }

}
