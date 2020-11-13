package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.context.utils.UtilsTree;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.core.foundation.BasePage;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.api.CmsRolePermissionService;
import com.cms.service.api.CmsRoleService;
import com.cms.service.dto.CmsPermissionDto;
import com.cms.service.dto.CmsRoleDto;
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
import java.util.ArrayList;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:40
 * @Version 1.0
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    CmsPermissionService cmsPermissionService;
    @Autowired
    CmsRoleService cmsRoleService;
    @Autowired
    CmsRolePermissionService cmsRolePermissionService;
    /**
     * 去角色首页
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("role","index");
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("role","add");
    }

    /**
     * 修改页面
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "id为空") Integer id,Model model){
        CmsRoleDto cmsRoleDto = cmsRoleService.getById(id);
        model.addAttribute("data",cmsRoleDto);
        return UtilsTemplate.adminTemplate("role","edit");
    }

    /**
     * 首页表格数据
     * @param cmsRoleDto
     * @return
     */
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsRoleDto cmsRoleDto){
        return Result.success(cmsRoleService.getPage(cmsRoleDto));
    }

    /**
     * 权限列表
     * @return
     */
    @PostMapping("permission.do")
    @ResponseBody
    public Result doPerimission(Integer roleId){
        ArrayList<CmsPermissionDto> list = (ArrayList<CmsPermissionDto>) cmsPermissionService.getTreeData(null);
        ArrayList<Integer> permissionIds = (ArrayList<Integer>) cmsRolePermissionService.getPermissionByRoleId(roleId);
        // 增加复选框属性
        UtilsTree.recursion(list,permissionIds);
        return Result.success(list);
    }

    /**
     * 角色添加
     * @param cmsRoleDto
     * @return
     */
    @PostMapping("add.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "添加角色")
    public Result doAdd(@Validated CmsRoleDto cmsRoleDto, BindingResult bindingResult){
        cmsRoleService.save(cmsRoleDto);
        return Result.success();
    }
    /**
     * 修改添加
     * @param cmsRoleDto
     * @return
     */
    @PostMapping("edit.do")
    @ResponseBody
    @DoLog(content = "修改角色")
    public Result doEdit(CmsRoleDto cmsRoleDto){
        cmsRoleService.update(cmsRoleDto);
        return Result.success();
    }
    /**
     * 角色删除
     * @param id 数据id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    @DoLog(content = "删除角色")
    public Result doDelete(@NotNull(message = "id不能为空") Integer id){
        cmsRoleService.deleteById(id);
        return Result.success();
    }



}
