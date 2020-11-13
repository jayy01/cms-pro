package com.cms.core.foundation;

import java.io.Serializable;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/12 10:32
 * @Version 1.0
 */
public interface BaseService<DTO extends BaseDto,PK extends Serializable> {

    /**
     * 通用保存
     * @param dto
     */
    void save(DTO dto);

    /**
     * 通用更新
     * @param dto
     */
    void update(DTO dto);
    /**
     * 根据id进行查找
     * @param id
     * @return
     */
    DTO getById(PK id);

    /**
     * 通用根据id删除
     * @param id
     */
    void deleteById(PK id);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    BasePage<DTO> getPage(DTO dto);

}
