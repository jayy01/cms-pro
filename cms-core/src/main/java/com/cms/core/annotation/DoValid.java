package com.cms.core.annotation;

import java.lang.annotation.*;

/**
 * @Author jayy
 * @Description  @Target(ElementType.METHOD)  修饰范围 方法
 *               @Retention(RetentionPolicy.RUNTIME) 注解不仅被保存在class中 jvm加载class文件之后 仍然存在
 *               @Documented
 * @Date 2020/10/26 18:25
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoValid {
}
