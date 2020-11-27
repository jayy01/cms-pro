package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsServletContext;
import com.cms.core.exception.BusinessException;
import com.cms.dao.enums.StaticSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.StaticPageService;
import com.cms.service.dto.CmsSiteDto;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/26 14:09
 * @Version 1.0
 */
@Service
@Slf4j
public class StaticPageServiceImpl implements StaticPageService {

    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private  UtilsServletContext servletContext;

    @Override
    public void toStaticIndex() {
        // 获取站点信息
        CmsSiteDto cmsSiteDto = cmsSiteService.getById(1);
        // 判断站点是否启用静态化
        if(BooleanUtils.isFalse(cmsSiteDto.getStaticIndex())){
            return;
        }
        // 静态化存放目录
        String staticDir = cmsSiteDto.getStaticDir();
        if(StringUtils.isEmpty(staticDir)){
            throw new BusinessException("请先在站点信息里维护静态化目录！");
        }
        // 静态后缀
        StaticSuffixEnum staticSuffix = cmsSiteDto.getStaticSuffix();
        // 首页模板路径
        //String templatePath = "/font/default/index." +staticSuffix.getLabel() ;
        String templatePath = cmsSiteDto.getTmplPath();
        // 输出路径
        String outPath = staticDir + "/index." + StaticSuffixEnum.HTML.getLabel();
        // 真实路径
        String realPath = servletContext.getRealPath(outPath);
        log.info("realPath:[{}]",realPath);
        File file = new File(realPath);
        File parentDir = file.getParentFile();
        if(!parentDir.exists()){
            parentDir.mkdirs();
        }
        HttpServletRequest request = UtilsHttp.getRequest();
        String contextPath = request.getContextPath();
        // 存放数据
        HashMap<String, Object> data = Maps.newHashMap();
        data.put("cmsSiteDto",cmsSiteDto);
        data.put("basePath",contextPath);
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(realPath),StandardCharsets.UTF_8)){
            // 获取到springmvc容器
            WebApplicationContext webApplicationContext = UtilsHttp.getWebApplicationContext(UtilsHttp.getRequest());
            // 获取freemarkerConfig的bean
            FreeMarkerConfig freeMarkerConfig = webApplicationContext.getBean(FreeMarkerConfig.class);
            // 获取config配置
            Configuration configuration = freeMarkerConfig.getConfiguration();
            // 配置
            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            // 获取到模板
            Template template = configuration.getTemplate(templatePath);
            // 模板写出
            template.process(data,writer);
        }catch (Exception e){
            log.error("生成首页模板失败");
            throw new BusinessException("生成首页模板失败");
        }
    }

    @Override
    public Boolean deleteStaticIndex() {
        CmsSiteDto cmsSiteDto = cmsSiteService.getById(1);
        String staticDir = cmsSiteDto.getStaticDir();
        HttpServletRequest request = UtilsHttp.getRequest();
        String contextPath = request.getContextPath();
        String realPath = servletContext.getRealPath(contextPath + "/" + staticDir + "/index." + StaticSuffixEnum.HTML.getLabel());
        File file = new File(realPath);
        return file.delete();
    }
}
