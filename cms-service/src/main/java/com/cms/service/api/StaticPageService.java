package com.cms.service.api;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/26 14:08
 * @Version 1.0
 */
public interface StaticPageService {
    /**
     * 首页静态化
     */
    void toStaticIndex();

    /**
     * 删除首页静态化
     * @return
     */
    Boolean deleteStaticIndex();
}
