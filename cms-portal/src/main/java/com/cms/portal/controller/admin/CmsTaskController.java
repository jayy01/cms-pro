package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.TaskExecutionCycleEnum;
import com.cms.dao.enums.TaskIntervalUnitEnum;
import com.cms.dao.enums.TaskTypeEnum;
import com.cms.service.api.CmsTaskService;
import com.cms.service.dto.CmsTaskDto;
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
 * @Description
 * @Date 2020/11/27 16:22
 * @Version 1.0
 */
@Controller
@RequestMapping("task")
public class CmsTaskController {

    @Autowired
    CmsTaskService cmsTaskService;
    /**
     * 去定时任务页
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("task","index");
    }

    /**
     * 定时任务添加页
     * @param model
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(Model model){
        model.addAttribute("typeList", TaskTypeEnum.values());
        model.addAttribute("executionCycleList", TaskExecutionCycleEnum.values());
        model.addAttribute("intervalUnitList", TaskIntervalUnitEnum.values());
        return UtilsTemplate.adminTemplate("task","add");
    }

    /**
     * 定时任务修改页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "id不能为空")Integer id, Model model){
        return UtilsTemplate.adminTemplate("task","edit");
    }

    /**
     * 查询
     * @param cmsTaskDto
     * @return
     */
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsTaskDto cmsTaskDto){
        cmsTaskService.getPage(cmsTaskDto);
        return Result.success();
    }


    /**
     * 定时任务添加
     * @param cmsTaskDto
     * @param bindingResult
     * @return
     */
    @DoValid
    @PostMapping("add.do")
    @ResponseBody
    public Result doAdd(@Valid CmsTaskDto cmsTaskDto, BindingResult bindingResult){
        cmsTaskService.save(cmsTaskDto);
        return Result.success();
    }

    /**
     * 定时任务修改
     * @param cmsTaskDto
     * @param bindingResult
     * @return
     */
    @DoValid
    @PostMapping("edit.do")
    @ResponseBody
    public Result doEdit(@Valid CmsTaskDto cmsTaskDto,BindingResult bindingResult){
        cmsTaskService.update(cmsTaskDto);
        return Result.success();
    }

    /**
     * 定时任务删除
     * @param id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(@NotNull(message = "id不能为空") Integer id){
        cmsTaskService.deleteById(id);
        return Result.success();
    }

}
