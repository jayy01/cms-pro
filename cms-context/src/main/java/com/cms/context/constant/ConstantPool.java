package com.cms.context.constant;

/**
 * @Author jayy
 * @Description 常量池
 * @Date 2020/10/22 18:26
 * @Version 1.0
 */

public class ConstantPool {

    private ConstantPool(){}
    /**
     * 操作提示相关
     */
    public static final String OPTION_SAVE_SUCCESS = "保存成功！";
    public static final String OPTION_UPDATE_SUCCESS = "修改成功！";
    public static final String OPTION_SAVE_ERROR = "保存失败！";
    public static final String OPTION_UPDATE_ERROR = "修改失败！";

    /**
     * 异常相关
     */
    public static final String EXCEPTION_NETWORK_ERROR = "网络繁忙，请稍后重试！";
    /**
     * 判断是否ajax请求
     */
    public static final String X_REQUESTED_WITH = "X-Requested-With";

}
