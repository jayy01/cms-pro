package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.StaticSuffixEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/26 13:30
 * @Version 1.0
 */
@Getter
@Setter
public class CmsSiteDto extends BaseDto<Integer> {
    @NotBlank(message = "请输入站点名称")
    private String siteName;
    private String keywords;
    private String description;
    private String staticDir;
    private StaticSuffixEnum staticSuffix;
    private Boolean staticIndex;
    private String tmplPath;
}
