package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/22 18:39
 * @Version 1.0
 */
@Controller
public class IndexController {
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("index");
    }
}
