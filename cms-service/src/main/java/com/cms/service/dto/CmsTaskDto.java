package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.TaskExecutionCycleEnum;
import com.cms.dao.enums.TaskIntervalUnitEnum;
import com.cms.dao.enums.TaskTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description 定时任务
 * @Date 2020/11/27 15:26
 * @Version 1.0
 */
@Getter
@Setter
public class CmsTaskDto extends BaseDto<Integer> {

    private String name;
    private String code;
    private TaskTypeEnum type;
    private TaskExecutionCycleEnum executionCycle;
    private Integer dayOfMonth;
    private Integer dayOfWeek;
    private Integer hour;
    private Integer minute;
    private Integer intervalHour;
    private Integer intervalMinute;
    private String cornExpression;
    private Boolean enable;
    private TaskIntervalUnitEnum intervalUnit;
    private String remark;

}
