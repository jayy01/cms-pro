package com.cms.portal.security.filter;


import com.alibaba.fastjson.JSON;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CmsLogService;
import com.cms.service.api.CmsUserService;
import com.cms.service.api.CommonService;
import com.cms.service.dto.CmsLogDto;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/28 14:44
 * @Version 1.0
 */
public class CmsAuthenticationfilter extends FormAuthenticationFilter {

    private static final String ADMIN_LOGIN = "/admin/cms/login.do";

    @Autowired
    CommonService commonService;
    @Autowired
    CmsUserService cmsUserService;
    @Autowired
    CmsLogService cmsLogService;

    /**
     * 重写isLoginRequest 判断是否为登录地址
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch(ADMIN_LOGIN, request);
    }

    /**
     * 重写executeLogin  判断账户 密码  验证码
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        //获取验证码病验证
        String result = commonService.verifyImageCaptcha(WebUtils.getCleanParam(request, "captcha"));
        //验证结果不为空 返回验证信息
        /*if(Objects.nonNull(result)){
            response.getWriter().write(JSON.toJSONString(Result.failed(result)));
            return false;
        }*/
        //实现登录
        Subject subject = UtilsShiro.getSubject();
        AuthenticationToken token = this.createToken(request, response);
        try {
            subject.login(token);
            onLoginSuccess(token,subject,request,response);
            response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            response.getWriter().write(JSON.toJSONString(Result.failed("用户名密码错误，请重新输入！")));
        } catch (DisabledAccountException e){
            response.getWriter().write(JSON.toJSONString(Result.failed(e.getMessage())));
        } catch (Exception e){
            // 用户可能登录成功  其它错误
            if(subject.isAuthenticated()){
                response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
            } else {
                response.getWriter().write(JSON.toJSONString(Result.failed("网络连接失败，请重新登录")));
            }
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        String ip = UtilsHttp.getRemoteAddress();
        CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
        //修改副表
        cmsUserDto.setLastLoginIp(ip);
        cmsUserDto.setSessionId((String) UtilsShiro.getSession().getId());
        //不重要  未实现事务
        cmsUserService.update(cmsUserDto);
        //记录日志
        cmsLogService.save(CmsLogDto.of(cmsUserDto.getId(),cmsUserDto.getUsername(),ip,url,"后台系统登录"));
        return false;
    }
}
