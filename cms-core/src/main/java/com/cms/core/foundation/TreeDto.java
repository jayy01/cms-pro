package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author jayy
 * @Description  树形结构属性
 * @Date 2020/11/8 18:41
 * @Version 1.0
 */
public class TreeDto<DTO extends BaseDto<PK>,PK extends Serializable> extends BaseDto<PK> {

    private List<DTO> children;
    /**
     * 树形结构的复选框树形
     */
    private List<Map<String,String>> checkArr;

    public List<DTO> getChildren() {
        return children;
    }

    public void setChildren(List<DTO> children) {
        this.children = children;
    }

    public List<Map<String, String>> getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(List<Map<String, String>> checkArr) {
        this.checkArr = checkArr;
    }
}
