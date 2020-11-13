package com.cms.portal.controller.admin;

import com.cms.context.constant.ConstantPool;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.PermissionTypeEnum;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 11:27
 * @Version 1.0
 */
@Controller
@RequestMapping("permission")
@Validated
public class PermissionController {

    @Autowired
    CmsPermissionService cmsPermissionService;

    /**
     * 进入权限管理页面
     * @param model
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(Model model){
        return UtilsTemplate.adminTemplate("permission","index");
    }

    /**
     * 进入权限添加页面
     * @param model
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(Integer parentId, Model model){
        if(Objects.nonNull(parentId)){
            model.addAttribute("parentId",parentId);
        }
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission","add");
    }
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "id为空") Integer id,Model model){
        CmsPermissionDto cmsPermissionDto = cmsPermissionService.getById(id);
        model.addAttribute("data",cmsPermissionDto);
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission","edit");
    }

    /**
     * 页面获取列表数据
     * @return
     */
    @GetMapping("list.do")
    @ResponseBody
    public Result doList(){
        return Result.success((ArrayList)cmsPermissionService.getList());
    }

    /**
     * 新建权限
     * @param cmsPermissionDto
     * @param bindingResult
     * @return
     */
    @PostMapping("add.do")
    @DoValid
    @ResponseBody
    public Result toSave(@Valid CmsPermissionDto cmsPermissionDto, BindingResult bindingResult){
        try {
            cmsPermissionService.save(cmsPermissionDto);
            return Result.success(ConstantPool.OPTION_SAVE_SUCCESS);
        }catch (Exception e){
            return Result.failed(ConstantPool.OPTION_SAVE_ERROR);
        }
    }

    /**
     * 修改权限
     * @param cmsPermissionDto
     * @param bindingResult
     * @return
     */
    @PostMapping("edit.do")
    @DoValid
    @ResponseBody
    public Result toEdit(@Valid CmsPermissionDto cmsPermissionDto, BindingResult bindingResult){
        try {
            cmsPermissionService.update(cmsPermissionDto);
            return Result.success(ConstantPool.OPTION_UPDATE_SUCCESS);
        }catch (Exception e){
            return Result.success(ConstantPool.OPTION_UPDATE_ERROR);
        }
    }

    /**
     *  下拉菜单请求
     * @param id
     * @return
     */
    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(Integer id){
        ArrayList permissionList = ((ArrayList) cmsPermissionService.getTreeData(id));
        return Result.success(permissionList);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    public Result doDeleteById(@NotNull(message = "id为空") Integer id){
        cmsPermissionService.deleteById(id);
        return Result.success();
    }
}
