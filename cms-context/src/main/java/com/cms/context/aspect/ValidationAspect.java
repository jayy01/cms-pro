package com.cms.context.aspect;

import com.cms.context.foundation.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/26 18:36
 * @Version 1.0
 */
@Component
@Aspect
public class ValidationAspect {
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.cms.core.annotation.DoValid)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if(arg instanceof BeanPropertyBindingResult){
                 BindingResult bindingResult = (BindingResult) arg;
                 if(bindingResult.hasErrors()){
                     return Result.failed(bindingResult.getFieldError().getDefaultMessage());
                 }
            }
        }
        return point.proceed();
    }

}
