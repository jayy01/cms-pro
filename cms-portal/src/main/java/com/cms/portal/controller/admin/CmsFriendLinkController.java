package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoValid;
import com.cms.core.foundation.BasePage;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.dto.CmsFriendLinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author jayy
 * @Description 友情链接
 * @Date 2020/11/16 18:02
 * @Version 1.0
 */
@Controller
@RequestMapping("friendLink")
public class CmsFriendLinkController {

    @Autowired
    CmsFriendLinkService cmsFriendLinkService;

    /**
     * 友情链接首页
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("friendLink","index");
    }

    /**
     * 添加页
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("friendLink","add");
    }

    /**
     * 修改页
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "id不能为空")Integer id, Model model){
        CmsFriendLinkDto cmsFriendLinkDto = cmsFriendLinkService.getById(id);
        model.addAttribute("data",cmsFriendLinkDto);
        return UtilsTemplate.adminTemplate("friendLink","edit");
    }

    /**
     * 首页查询
     * @param cmsFriendLinkDto
     * @return
     */
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsFriendLinkDto cmsFriendLinkDto){
        BasePage<CmsFriendLinkDto> page = cmsFriendLinkService.getPage(cmsFriendLinkDto);
        return Result.success(page);
    }

    /**
     * 添加友情链接
     * @param cmsFriendLinkDto
     * @param bindingResult
     * @return
     */
    @PostMapping("add.do")
    @ResponseBody
    @DoValid
    public Result doAdd(@Valid CmsFriendLinkDto cmsFriendLinkDto, BindingResult bindingResult){
        cmsFriendLinkService.save(cmsFriendLinkDto);
        return Result.success();
    }
    /**
     * 修改友情链接
     * @param cmsFriendLinkDto
     * @param bindingResult
     * @return
     */
    @PostMapping("edit.do")
    @ResponseBody
    @DoValid
    public Result doEdit(@Valid CmsFriendLinkDto cmsFriendLinkDto, BindingResult bindingResult){
        cmsFriendLinkService.update(cmsFriendLinkDto);
        return Result.success();
    }
    /**
     * 修改友情链接
     * @param id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(@NotNull(message = "id不能为空") Integer id){
        cmsFriendLinkService.deleteById(id);
        return Result.success();
    }


}
