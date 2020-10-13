package com.cms.core.foundation;

import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 10:32
 * @Version 1.0
 */
public interface BaseMapper<ENTITY extends BaseEntity<PK>,PK extends Serializable> {
    /**
     * 根据id查找
     * @param id
     * @return
     */
    ENTITY selectById(Integer id);
}
