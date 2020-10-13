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
     * 根据id进行查找
     * @param id
     * @return
     */
    DTO getById(PK id);

}
