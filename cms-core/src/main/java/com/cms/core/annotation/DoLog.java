package com.cms.core.annotation;

import java.lang.annotation.*;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/27 9:43
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoLog {
    /**
     * 日志内容
     * @return
     */
    String content();
}
