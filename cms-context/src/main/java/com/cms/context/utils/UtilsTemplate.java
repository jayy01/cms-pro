package com.cms.context.utils;

/**
 * @Author jayy
 * @Description 模板工具类
 * @Date 2020/10/22 19:08
 * @Version 1.0
 */
public class UtilsTemplate {

    private UtilsTemplate(){}

    /**
     * 后台模板方法
     * @param template
     * @return
     */
    public static String adminTemplate(String template){
        return "admin/"+ template;
    }

    /**
     * 后台带目录模板方法
     * @param dir
     * @param template
     * @return
     */
    public static String adminTemplate(String dir,String template){
        return "admin/"+dir+"/"+template;
    }

    public static String fontTemplate(String template){
        return "font/" + template;
    }
}
