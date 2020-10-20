package com.cms.portal.controller.admin;

import com.cms.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/28 11:28
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    Producer kaptchaProducer;
    @Autowired
    CommonService commonService;

    @GetMapping("login.do")
    public String toLogin(){
        return "/admin/login";
    }

    @GetMapping("captcha.do")
    public void doCaptcha(HttpServletResponse httpServletResponse) {
        commonService.imageCaptcha();
    }
}
