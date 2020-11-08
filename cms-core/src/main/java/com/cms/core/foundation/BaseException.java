package com.cms.core.foundation;

import java.text.MessageFormat;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/3 13:20
 * @Version 1.0
 */
public class BaseException extends RuntimeException {

    public Integer code;
    public String msg;

    public BaseException(Integer code, String msg) {
        // 调用父类的构造方法  传false不开启栈追踪
        super(msg,null,false,false);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[0]{1}",code,msg);

    }
}
