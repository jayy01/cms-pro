package com.cms.portal.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/20 17:26
 * @Version 1.0
 */
public class CmsViewResolver extends FreeMarkerView {
    private static final String ADMIN_PATH = "/admin/cms";

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();  //
        String servletPath = request.getServletPath(); // /admin/cms
        //后台路径
        if(requestURI.contains(ADMIN_PATH)){
            model.put("adminPath",contextPath+servletPath);
        }
        model.put("basePath",contextPath);
    }
}
