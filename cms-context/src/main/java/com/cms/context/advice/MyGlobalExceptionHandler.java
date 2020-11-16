package com.cms.context.advice;

import com.cms.context.constant.ConstantPool;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsHttp;
import com.cms.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/3 10:23
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class MyGlobalExceptionHandler {

    /**
     * 拦截参数校验错误异常 ajax请求返回json数据  非ajax请求重定向错误页面
     * @param e ConstraintViolationException 参数校验不通过抛出异常
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> item : constraintViolations){
            log.info(item.getPropertyPath().toString() + item.getMessage());
            return UtilsHttp.responseExceptionHandler(item.getMessage(),"/error.do");
        }
        return Result.failed(ConstantPool.EXCEPTION_NETWORK_ERROR);
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public  Result<String> businessExceptionHandler(BusinessException e){
        return Result.failed(e.getMsg());
    }

    /**
     * 没有权限报错
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Result<String> unauthorizedException(UnauthorizedException e){
        return Result.failed("没有权限访问");
    }

    /**
     *  运行时异常拦截处理
     * @param e  运行时异常
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionException(RuntimeException e){
        return Result.failed(e.getMessage());
    }

}
