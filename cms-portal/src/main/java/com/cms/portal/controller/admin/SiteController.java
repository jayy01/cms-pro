package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsServletContext;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.StaticSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Author jayy
 * @Description 站点控制器
 * @Date 2020/10/26 13:19
 * @Version 1.0
 */
@Controller
@RequestMapping("site")
public class SiteController {

    @Autowired
    CmsSiteService cmsSiteService;
    @Autowired
    UtilsServletContext servletContext;

    /**
     * 去站点设置
     *
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(Model model) {
        model.addAttribute("selectList", StaticSuffixEnum.values());
        model.addAttribute("data", cmsSiteService.getById(1));
        model.addAttribute("tmplPath",servletContext.getTmplPathList("index","index"));
        return UtilsTemplate.adminTemplate("site", "index");
    }

    @PostMapping("edit.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "站点修改")
    public Result doEdit(@Valid CmsSiteDto cmsSiteDto, BindingResult bindingResult) {
        cmsSiteService.update(cmsSiteDto);
        return Result.success();
    }

}
