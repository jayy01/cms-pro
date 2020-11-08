package com.cms.portal.interceptor;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.core.annotation.DoLog;
import com.cms.service.api.CmsLogService;
import com.cms.service.dto.CmsLogDto;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 9:50
 * @Version 1.0
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CmsLogService cmsLogService;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        DoLog doLog = handlerMethod.getMethodAnnotation(DoLog.class);
        Optional.ofNullable(doLog).ifPresent(x->{
            String url = request.getRequestURI();
            Subject subject = UtilsShiro.getSubject();
            CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
            String ip = UtilsHttp.getRemoteAddress();
            threadPoolTaskExecutor.execute(()->{
                cmsLogService.save(CmsLogDto.of(cmsUserDto.getId(),cmsUserDto.getUsername(),ip,url,doLog.content()));
            });
        });
        super.postHandle(request, response, handler, modelAndView);
    }
}
