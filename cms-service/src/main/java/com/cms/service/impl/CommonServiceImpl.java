package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 15:47
 * @Version 1.0
 */
@Service
public class CommonServiceImpl implements CommonService {

    private static String IMAGE_CAPTCHA_SUFFIX = "image_captch";

    @Autowired
    private Producer kaptchaProducer;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void imageCaptcha() {
        String text = kaptchaProducer.createText( );
        // 获取Shiro中的sessionId  将验证码存到redis 60秒失效  最后参数为单位
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId()+IMAGE_CAPTCHA_SUFFIX,text,60, TimeUnit.SECONDS);
        HttpServletResponse response = UtilsHttp.getResponse();
        // 设置图像响应的格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        try (ServletOutputStream outputStream = response.getOutputStream()){
            ImageIO.write(kaptchaProducer.createImage(text),"jpg",outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String verifyImageCaptcha(String captcha) {
        String redisCaptch = redisTemplate.opsForValue().get(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX);
        if(Objects.isNull(redisCaptch)){
            return "验证码为空或已失效，请重新输入";
        }
        if(!StringUtils.equals(redisCaptch,captcha)){
            return "验证码输入错误";
        }
        return null;
    }
}
