package com.cms.dao.enums.converter;

import com.cms.core.foundation.BaseEnum;
import com.cms.dao.enums.*;

import java.util.Objects;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 10:49
 * @Version 1.0
 */
public class EnumConverter {
    /**
     * 用于将entity中int类型转换为userStatusEnum枚举类型
     * @param status
     * @return
     */
    public static UserStatusEnum toUserStatusEnum (int status){
        return (UserStatusEnum)converter(UserStatusEnum.values(), status);
    }
    /**
     * 用于将entity中int类型转换为StaticSuffixEnum枚举类型
     * @param status
     * @return
     */
    public static StaticSuffixEnum toStaticSuffixEnum (int status){
        return (StaticSuffixEnum)converter(StaticSuffixEnum.values(), status);
    }
    /**
     * 用于将entity中int类型转换为TaskExecutionCycleEnum枚举类型
     * @param status
     * @return
     */
    public static TaskExecutionCycleEnum toTaskExecutionCycleEnum (int status){
        return (TaskExecutionCycleEnum)converter(TaskExecutionCycleEnum.values(), status);
    }
    /**
     * 用于将entity中int类型转换为TaskIntervalUnitEnum枚举类型
     * @param status
     * @return
     */
    public static TaskIntervalUnitEnum toTaskIntervalUnitEnum (int status){
        return (TaskIntervalUnitEnum)converter(TaskIntervalUnitEnum.values(), status);
    }
    /**
     * 用于将entity中int类型转换为StaticSuffixEnum枚举类型
     * @param status
     * @return
     */
    public static TaskTypeEnum toTaskTypeEnum (int status){
        return (TaskTypeEnum)converter(TaskTypeEnum.values(), status);
    }

    /**
     * 将dto中的枚举转换为entity中的Integer
     * @param <E>
     * @return
     */
    public static<E extends BaseEnum> Integer enumToInteger(E enumeration){
        return (enumeration!=null)?enumeration.getOrdinal():null;
    }
    /**
     * 通用枚举转换器 统一循环枚举比对
     * @param enums
     * @param status
     * @return
     */
    public static BaseEnum converter(BaseEnum[] enums,int status){

        for(BaseEnum enumeration:enums){
            if(Objects.equals(enumeration.getOrdinal(),status)){
                return  enumeration;
            }
        }
        return null;
    }

}
