package com.cms.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * 失败访问
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.saveRequest(request);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        WebUtils.issueRedirect(request, response,(requestURI.startsWith(adminPrefix))?adminLoginUrl:this.getLoginUrl());
        return false;
    }
}
