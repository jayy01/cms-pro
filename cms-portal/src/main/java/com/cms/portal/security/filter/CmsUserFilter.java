package com.cms.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;

/**
 * @Author jayy
 * @Description 用户拦截器
 * @Date 2020/9/28 14:46
 * @Version 1.0
 */
@Getter
@Setter
public class CmsUserFilter extends UserFilter {

    private String adminPrefix;
    private String adminLoginUrl;

}
