package com.cms.context.intercepter;

import com.cms.core.foundation.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/22 17:43
 * @Version 1.0
 */
@Intercepts(
        @Signature(type = Executor.class,method = "update",args ={MappedStatement.class,Object.class})
)
public class BaseIntercepter implements Interceptor {
    /**
     * 实现包装逻辑
     *
     * @param invocation invocation中可以拿到方法  参数  对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取都一个
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 获取sql的执行类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        switch (sqlCommandType) {
            case INSERT:
                insert(invocation.getArgs()[1]);
                break;
            case UPDATE:
                update(invocation.getArgs()[1]);
                break;
            default:
                break;
        }

        return invocation.proceed();
    }

    /**
     * 插入操作
     * @param obj
     */
    private void insert(Object obj){
        if(obj instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setCreateTime(LocalDateTime.now());
        }
    }

    /**
     * 更新操作
     * @param obj
     */
    private void update(Object obj){
        if(obj instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setUpdateTime(LocalDateTime.now());
        }
    }


    /**
     * 代理对象 默认需要返回target
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * 配置
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
