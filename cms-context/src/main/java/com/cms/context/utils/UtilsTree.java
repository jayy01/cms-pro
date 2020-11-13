package com.cms.context.utils;

import com.cms.core.foundation.TreeDto;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 18:46
 * @Version 1.0
 */
public class UtilsTree {

    /**
     * 树形结构增加复选框属性
     * @param list  属性数据列表
     * @param <PK>  主键
     * @param <T> 列表内DTO对象
     */
    public static<PK extends Serializable,T extends TreeDto<T,PK>> void recursion(List<T> list,List<PK> permissionIds){
        list.forEach(x -> {
            List<Map<String, String>> checked = Collections.singletonList(Collections.singletonMap("checked", "0"));
            if(!CollectionUtils.isEmpty(permissionIds)){
                if(permissionIds.contains(x.getId())){
                    checked = Collections.singletonList(Collections.singletonMap("checked","1"));
                }
            }
            x.setCheckArr(checked);
            if(!CollectionUtils.isEmpty(x.getChildren())){
                recursion(x.getChildren(),permissionIds);
            }
        });
    }
}
