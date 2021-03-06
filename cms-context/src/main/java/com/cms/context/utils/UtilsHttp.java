package com.cms.context.utils;

import com.cms.context.constant.ConstantPool;
import com.cms.context.foundation.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author jayy
 * @Description 自己封装的http工具类
 * @Date 2020/9/29 15:59
 * @Version 1.0
 */
@Slf4j
public class UtilsHttp {

    private static final String HEADER_REAL_IP = "X-Real-IP";
    private static final String UNKNOWN = "unknown";
    private static final String SLASH = "../";
    private static final String BACKSLASH = "..\\";
    private static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";
    private static final String COMMA = ",";
    private static final String IP_EMPTY = "0:0:0:0:0:0:0:1";
    private static final String IP_LOOP = "127.0.0.1";

    // 分页信息
    private static final Integer DEFAULT_SIZE = 10;
    private static final String ORDER_BY = "orderBy";
    private static final String PAGE_SIZE = "pageSize";
    private static final String PAGE_CURRENT = "pageCurrent";

    /**
     * 获取访问者ip
     * 在一般情况下 使用 Request.getRemoteAddress() 即可 但是经过nginx等反向代理软件后 这个方法会失效
     * 本方法先从Header中获取X-Real-IP 如果不存在再从X-Forwarded-For获得第一个IP（用，分割）
     * 如果本方法不存在则调用Request.getremoteAddress()。
     * @return
     */
    public static String getRemoteAddress(){
        HttpServletRequest request = getRequest();
        String ip = request.getHeader(HEADER_REAL_IP);

        if(!StringUtils.isBlank(ip)&&!UNKNOWN.equalsIgnoreCase(ip)){
            if(ip.contains(SLASH) || ip.contains(BACKSLASH)){
                return "";
            }
            return ip;
        }
        ip = request.getHeader(HEADER_FORWARDED_FOR);
        if(!StringUtils.isBlank(ip)&&!UNKNOWN.equalsIgnoreCase(ip)){
            // 多次反向代理后会有多个ip  第一个为真是ip
            int index = ip.indexOf(COMMA);
            if(index != -1){
                ip = ip.substring(0, index);
            }
            if(ip.contains(SLASH) || ip.contains(BACKSLASH)){
                return "";
            }
            return ip;
        }else{
            ip = request.getRemoteAddr();
            if(ip.contains(SLASH) || ip.contains(BACKSLASH)){
                return "";
            }
            if(ip.equals(IP_EMPTY)){
                return IP_LOOP;
            }
            return ip;
        }
    }

    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取springmvc的web容器
     * @param request
     * @return
     */
    public static WebApplicationContext getWebApplicationContext(ServletRequest request){
        return ((WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE));
    }

    /**
     * 获取response对象
     * @return
     */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

    /**
     * 响应异常处理  区分ajax请求与跳转页面请求
     * @return
     */
    public static Result<String> responseExceptionHandler(String info,String path){
        HttpServletRequest request = getRequest();
        if(Objects.isNull(request.getHeader(ConstantPool.X_REQUESTED_WITH))){
            HttpServletResponse response = getResponse();
            try {
                response.sendRedirect(request.getServletPath()+path);
                return  null;
            } catch (IOException e) {
                log.error("页面跳转异常："+e.getMessage());
            }
        }
        return Result.failed(info);
    }

    /**
     * 获取分页查询的参数
     * @return
     */
    public static Page getPageInfo(){
        HttpServletRequest request = getRequest();
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageCurrent = request.getParameter(PAGE_CURRENT);
        String orderBy = request.getParameter(ORDER_BY);

        return new Page(StringUtils.isNotBlank(pageSize)?Integer.parseInt(pageSize):DEFAULT_SIZE,
                        StringUtils.isNotBlank(pageCurrent)?Integer.parseInt(pageCurrent):1,
                        orderBy);
    }
    // 内部类提供返回对象
    public static final class Page{
        private Integer pageSize;
        private String orderBy;
        private Integer pagecurrent;

        public Page(Integer pageSize, Integer pagecurrent, String orderBy) {
            this.pageSize = pageSize;
            this.orderBy = orderBy;
            this.pagecurrent = pagecurrent;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public Integer getPagecurrent() {
            return pagecurrent;
        }

        public void setPagecurrent(Integer pagecurrent) {
            this.pagecurrent = pagecurrent;
        }
    }

}
