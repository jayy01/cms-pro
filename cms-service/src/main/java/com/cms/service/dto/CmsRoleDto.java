package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:36
 * @Version 1.0
 */
@Getter
@Setter
public class CmsRoleDto extends BaseDto<Integer> {

    @NotEmpty(message = "角色名称不能为空")
    private String name;
    private String priority;
    private Boolean all;
    private boolean status;
    /**
     * 权限
     */
    private List<Integer> permission;

}
