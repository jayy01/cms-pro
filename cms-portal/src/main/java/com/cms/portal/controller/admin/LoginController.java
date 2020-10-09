package com.cms.portal.controller.admin;

import com.cms.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @GetMapping("captcha.do")
    public void doCaptcha(HttpServletResponse httpServletResponse){
        commonService.imageCaptcha();
    }
}
