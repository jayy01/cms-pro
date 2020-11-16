package com.cms.service.api;

import com.cms.core.foundation.BasePage;
import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserDto;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/30 9:30
 * @Version 1.0
 */
public interface CmsUserService extends BaseService<CmsUserDto,Integer> {
    /**
     * 根据用户名称查询
     * @param username
     * @return
     */
    CmsUserDto selectByUsername(String username);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    CmsUserDto getByEmail(String email);
    /**
     * 获取所有数据 分页
     * @param cmsUserDto
     * @return
     */
    BasePage<CmsUserDto> getPage(CmsUserDto cmsUserDto) ;

    /**
     * 根据id更新登陆次数
     * @param id
     */
    void updateLoginCount(Integer id);

}
