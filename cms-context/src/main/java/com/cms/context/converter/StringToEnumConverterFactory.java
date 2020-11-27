package com.cms.context.converter;

import com.cms.core.foundation.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Objects;
import java.util.WeakHashMap;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/26 10:11
 * @Version 1.0
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final WeakHashMap<String,Converter> CACHE_MAP = new WeakHashMap<>();

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> aClass) {
        String simpleName = aClass.getSimpleName();
        Converter converter = CACHE_MAP.get(simpleName);
        if(Objects.isNull(converter)){
            converter = new Converter<String, T>() {
                @Override
                public T convert(String s) {
                    for (T e : aClass.getEnumConstants()) {
                        if (Objects.equals(e.getOrdinal(), Integer.parseInt(s))) {
                            return e;
                        }
                    }
                    return null;
                }
            };
            CACHE_MAP.put(simpleName,converter);
        }

        return converter;
    }
}
