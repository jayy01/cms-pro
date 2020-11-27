package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.service.api.StaticPageService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author jayy
 * @Description 页面静态化
 * @Date 2020/11/26 14:06
 * @Version 1.0
 */

@Controller
@RequestMapping("static")
public class StaticPageController {

    @Autowired
    StaticPageService staticPageService;

    /**
     * 首页静态化
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){

        return UtilsTemplate.adminTemplate("staticPage","index");
    }


    /**
     * 首页静态化
     * @return
     */
    @PostMapping("index.do")
    @ResponseBody
    public Result doIndexStatic(){
        staticPageService.toStaticIndex();
        return Result.success();
    }

    /**
     * 删除首页静态化
     * @return
     */
    @PostMapping("deleteIndex.do")
    @ResponseBody
    public Result deleteIndex(){
        Boolean staticIndex = staticPageService.deleteStaticIndex();
        if(BooleanUtils.isTrue(staticIndex)){
            return Result.success("删除成功");
        }
        return Result.failed("删除失败,请检查是否生成静态页面");
    }
}
