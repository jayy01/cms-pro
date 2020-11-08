package com.cms.core.exception;

import com.cms.core.enums.RestCodeEnum;
import com.cms.core.foundation.BaseEnum;
import com.cms.core.foundation.BaseException;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/3 13:24
 * @Version 1.0
 */
public class BusinessException extends BaseException {

    /**
     * 带有错误信息的构造函数
     * @param msg
     */
    public BusinessException(String msg) {
        super(RestCodeEnum.ERROR.getOrdinal(),msg);
    }

    /**
     *  带有错误码和错误消息的构造函数
     * @param enums  枚举类
     * @param msg   错误信息
     */
    public BusinessException(BaseEnum enums, String msg) {
        super(enums.getOrdinal(), msg);
    }

    /**
     * 重写 throwable的fillInStackTrace  返回this  屏蔽栈追踪形成（不用）
     * @return
     */
    /*@Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }*/
}
