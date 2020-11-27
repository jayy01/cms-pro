package com.cms.portal.controller.font;

import com.cms.context.utils.UtilsServletContext;
import com.cms.context.utils.UtilsTemplate;
import com.cms.dao.enums.StaticSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsSiteDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/22 18:22
 * @Version 1.0
 */
@Slf4j
@Controller
public class DynamicPageController {
    @Autowired
    CmsSiteService cmsSiteService;
    @Autowired
    UtilsServletContext utilsServletContext;
    @GetMapping("index.shtml")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        CmsSiteDto cmsSiteDto = cmsSiteService.getById(1);
        if(BooleanUtils.isTrue(isExist(cmsSiteDto))){
            String contextPath = request.getContextPath();
            try {
                response.sendRedirect(contextPath+"/"+cmsSiteDto.getStaticDir()+"/index."+ StaticSuffixEnum.HTML.getLabel());
            } catch (IOException e) {
                log.error("跳转页面失败=[{}]",e);
            }
        }
        model.addAttribute("cmsSiteDto",cmsSiteDto);
        return UtilsTemplate.fontTemplate("index","index");
    }

    /**
     * 是否有静态化首页
     * @param cmsSiteDto
     * @return
     */
    private Boolean isExist(CmsSiteDto cmsSiteDto){
        if(BooleanUtils.isTrue(cmsSiteDto.getStaticIndex())){
            String staticDir = cmsSiteDto.getStaticDir();
            return FileUtil.canReadFile(new File(utilsServletContext.getRealPath(staticDir + "/index." + StaticSuffixEnum.HTML.getLabel())));
        }
        return false;
    }
}
