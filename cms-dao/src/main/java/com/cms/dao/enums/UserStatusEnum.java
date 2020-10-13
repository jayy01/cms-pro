package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 10:09
 * @Version 1.0
 */
@Getter
public enum UserStatusEnum implements BaseEnum {

    NORMAL(1,"正常"),
    DISABLED(2,"禁用"),
    LOCKED(3,"锁定"),
    UNACTIVATED(4,"未激活");

    private int ordinal;
    private String label;

    UserStatusEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
