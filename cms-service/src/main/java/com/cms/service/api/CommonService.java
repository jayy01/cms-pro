package com.cms.service.api;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 15:46
 * @Version 1.0
 */
public interface CommonService {
    /**
     * 获取图片验证码
     */
    void imageCaptcha();
    /**
     * 验证验证码
     */
    String verifyImageCaptcha(String captcha);
}
