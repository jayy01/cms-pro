package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoValid;
import com.cms.core.foundation.BasePage;
import com.cms.service.api.CmsRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author jayy
 * @Description 后台管理员
 * @Date 2020/11/13 15:43
 * @Version 1.0
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    CmsUserService cmsUserService;
    @Autowired
    CmsRoleService cmsRoleService;
    /**
     * 管理员首页
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin","index");
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(Model model){
        // 获取角色列表
        model.addAttribute("roles", cmsRoleService.getList(null));
        return UtilsTemplate.adminTemplate("admin","add");
    }

    /**
     * 首页数据
     * @param cmsUserDto
     * @return
     */
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsUserDto cmsUserDto){
        return Result.success(cmsUserService.getPage(cmsUserDto));
    }

    /**
     * 添加数据
     * @param cmsUserDto
     * @param bindingResult
     * @return
     */
    @PostMapping("add.do")
    @ResponseBody
    @DoValid
    public Result doAdd(@Validated CmsUserDto cmsUserDto, BindingResult bindingResult){
        cmsUserService.save(cmsUserDto);
        return Result.success();
    }

    @RequestMapping("edit.do")
    @ResponseBody
    @DoValid
    public Result doEdit(@Validated CmsUserDto cmsUserDto,BindingResult bindingResult){
        cmsUserService.update(cmsUserDto);
        return Result.success();
    }

}
