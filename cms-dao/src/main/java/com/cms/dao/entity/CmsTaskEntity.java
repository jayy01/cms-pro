package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author jayy
 * @Description 定时任务
 * @Date 2020/11/27 15:20
 * @Version 1.0
 */
@Getter
@Setter
public class CmsTaskEntity extends BaseEntity<Integer> {

   private String name;
   private String code;
   private Integer type;
   private Integer executionCycle;
   private Integer dayOfMonth;
   private Integer dayOfWeek;
   private Integer hour;
   private Integer minute;
   private Integer intervalHour;
   private Integer intervalMinute;
   private String cornExpression;
   private Boolean enable;
   private Integer intervalUnit;
   private String remark;

}
