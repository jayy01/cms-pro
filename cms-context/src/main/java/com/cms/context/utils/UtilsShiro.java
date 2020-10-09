package com.cms.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Author jayy
 * @Description  自己封装的Shiro工具类
 * @Date 2020/9/29 15:52
 * @Version 1.0
 */
public class UtilsShiro {
    /**
     * 通过Shiro获取sessionId
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取shiro的subject
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
}
