package com.cms.portal.controller.admin;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoValid;
import com.cms.core.foundation.BasePage;
import com.cms.service.api.CmsRoleService;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    @Autowired
    CmsUserRoleService cmsUserRoleService;
    /**
     * 管理员首页
     * @return
     */
    @RequiresPermissions(value = {"admin:index"})
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin","index");
    }

    /**
     * 添加页面
     * @return
     */
    @RequiresPermissions(value = {"admin:add"})
    @GetMapping("add.do")
    public String toAdd(Model model){
        // 获取角色列表
        model.addAttribute("roles", cmsRoleService.getList(null));
        return UtilsTemplate.adminTemplate("admin","add");
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "id不能为空") Integer id, Model model){
        // 获取角色列表
        model.addAttribute("roles", cmsRoleService.getList(null));
        CmsUserDto cmsUserDto = cmsUserService.getById(id);
        cmsUserDto.setPassword(null);
        cmsUserDto.setSalt(null);
        Integer roleId = cmsUserRoleService.getByUserId(id);
        cmsUserDto.setRoleId(roleId);
        model.addAttribute("data", cmsUserDto);
        return UtilsTemplate.adminTemplate("admin","edit");
    }

    /**
     * 首页数据
     * @param cmsUserDto
     * @return
     */
    @FastJsonView(exclude = {
        @FastJsonFilter(clazz = CmsUserDto.class,props = {"password","salt"})
    })
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
    @RequiresPermissions(value = {"admin:add"})
    @PostMapping("add.do")
    @ResponseBody
    @DoValid
    public Result doAdd(@Validated CmsUserDto cmsUserDto, BindingResult bindingResult){
        CmsUserDto byUsername = cmsUserService.selectByUsername(cmsUserDto.getUsername());
        if (Objects.isNull(byUsername)){
            return Result.failed("用户名已注册，请重新输入！");
        }
        cmsUserService.getByEmail(cmsUserDto.getEmail());
        cmsUserService.save(cmsUserDto);
        return Result.success();
    }

    /**
     * 修改
     * @param cmsUserDto
     * @param bindingResult
     * @return
     */
    @RequestMapping("edit.do")
    @ResponseBody
    @DoValid
    public Result doEdit(@Validated CmsUserDto cmsUserDto,BindingResult bindingResult){
        cmsUserService.update(cmsUserDto);
        return Result.success();
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(@NotNull(message = "id不能为空") Integer id){
        cmsUserService.deleteById(id);
        return Result.success();
    }


}
