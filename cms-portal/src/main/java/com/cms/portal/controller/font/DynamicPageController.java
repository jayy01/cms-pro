package com.cms.portal.controller.font;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/22 18:22
 * @Version 1.0
 */
@Controller
public class DynamicPageController {
    @GetMapping("index.shtml")
    public String index(){

        return "";
    }
}
