package com.cms.core.foundation;

import java.io.Serializable;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 10:32
 * @Version 1.0
 */
public interface BaseMapper<ENTITY extends BaseEntity<PK>, PK extends Serializable> {

    /**
     * 通用保存
     *
     * @param entity
     */
    void save(ENTITY entity);

    /**
     * 通用更新
     * @param entity
     */
    void update(ENTITY entity);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    ENTITY selectById(PK id);
    /**
     * 通用根据id删除
     * @param id
     */
    void deleteById(PK id);
}
