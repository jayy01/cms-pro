package com.cms.portal.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
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

        List<String> includeGoBackList = Arrays.asList("add.do", "edit.do","error.do");

        //后台路径
        if(requestURI.contains(ADMIN_PATH)){
            model.put("adminPath",contextPath+servletPath);
            //判断返回按钮
            includeGoBackList .forEach(x -> {
                if (requestURI.contains(x)){
                    model.put("goBack",true);
                    model.put("operationUrl",x);
                }
            });
        }

        model.put("basePath",contextPath);
    }
}
