package com.cms.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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

    /**
     * 获取随机盐
     * @return
     */
    public static String getSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * SHA-256散列加密
     * @param content
     * @param salt
     * @param hashIterations
     * @return
     */
    public static String sha256(String content,String salt,Integer hashIterations){
        return new SimpleHash("SHA-256",content,salt,hashIterations).toHex();
    }
}
