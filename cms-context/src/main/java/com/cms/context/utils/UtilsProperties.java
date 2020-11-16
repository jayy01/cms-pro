package com.cms.context.utils;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @Author jayy
 * @Description properties 工具类
 * @Date 2020/11/16 10:30
 * @Version 1.0
 */
@Component
public class UtilsProperties implements EmbeddedValueResolverAware {

    private  StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * 获取properties属性值
     * @param name
     * @return
     */
    public String getPropertiesValue(String name){
        return resolver.resolveStringValue("${"+ name +"}");
    }
}
