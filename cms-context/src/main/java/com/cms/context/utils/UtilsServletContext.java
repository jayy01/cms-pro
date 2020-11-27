package com.cms.context.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/26 15:00
 * @Version 1.0
 */
@Component
public class UtilsServletContext implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 获取真实路径
     *
     * @param path
     * @return
     */
    public String getRealPath(String path) {
        return servletContext.getRealPath(StringUtils.isBlank(path) ? "/" : path);
    }

    /**
     * 获取模板路径
     * @param tmplDirName 模板文件夹 default后
     * @param tmplPrefix 筛选模板的前缀
     * @return
     */
    public List<String> getTmplPathList(String tmplDirName, String tmplPrefix) {
        String dirPath = "/WEB-INF/font/default/" + tmplDirName;
        File file = new File(getRealPath(dirPath));
        File webInfoFile = new File(getRealPath("/WEB-INF/"));
        Collection<File> files = FileUtils.listFiles(file, FileFilterUtils.prefixFileFilter(tmplPrefix), null);
        return files.stream().map(x -> StringUtils.substring(StringUtils.replace(x.getAbsolutePath(), File.separator, "/"), webInfoFile.getAbsolutePath().length()))
                .collect(Collectors.toList());
    }

}
