let core = {
    //限流工具类  方法  参数 作用域
    throttle:function (method,arg,context) {
        //进来一次  取消前一个定时
        clearTimeout(method.tId);
        method.tId = setTimeout(function () {
            method.call(context,arg);
        },100)
    },
    //ajax请求封装
    http:function (option,callback) {
        this.cancel && this.cancel.abort();
        let opt = {load:true},loadHandler,loadTime, options = {
            url:"",
            method:"post",
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            beforeSend:function(){
                this.load && (loadTime = new Date().getTime()) && (loadHandler = LayUtil.layer.init(function (inner,layer) {
                    layer.load({shade:0.1});
                }))
            },
            success:function (res) {
                //成功返回后关闭load加载
                if (this.load && loadHandler) {
                    let time = 0;
                    //如果请求小于500 则在延迟500关闭
                    if(new Date().getTime() - loadTime < 500){
                        time = 500;
                    }
                    setTimeout(function () {
                        loadHandler.closeLoading();
                        /*if(res.restCode === CONSTANT.HTTP.ERROR){
                            LayUtil.layer.msg(res.info,{icon: 5,time:300})
                        }*/
                        switch (res.restCode) {
                            case CONSTANT.HTTP.SUCCESS:
                                core.prompt.msg(res.info,{icon: 1,time:300})
                                break;
                            case CONSTANT.HTTP.ERROR:
                                core.prompt.msg(res.info,{icon: 5,time:300})
                                break;
                        }
                    },time)
                }
                //处理自定义回调
                (callback instanceof Function) && callback(res)
            },
            error:function (XMLHttpRequest, textStatus, errorThrow) {
                loadHandler.closeLoading();
                core.prompt.msg("系统异常，请重试！",{icon: 5,time:1000},null);
            }
        }
        Object.assign(opt,option,options);// 自己传进来的参数覆盖默认的 赋值于一个新的对象 es6语法
        this.cancel = $.ajax(opt);
    },
    //提示相关
    prompt:{
        msg:function (info, option, callback) {
            LayUtil.layer.init(function (inner) {
                inner.msg(info,option,callback)
            })
        }
    }
};
/*定义常量值*/
const CONSTANT = {
    //http相关
    HTTP:{
        SUCCESS : 200,
        ERROR : 500
    }
}


//layui工具类
function LayUtil() {

}

//js原型链
LayUtil.prototype = {
    construct: LayUtil,
    //弹窗 使用自执性匿名函数
    layer:(function (LayUtil) {
        //函数  自动实例化
        function Inner() {

        }
        Inner.prototype = {
            construct:Inner,
            //初始化
            init:function (callback) {
                let that = this; //传递作用域
                layui.use('layer',function () {
                    that.layer = layui.layer; //传递到上层
                    if(callback instanceof Function){
                        callback(that,that.layer);
                    }
                })
                return this;
            },
            //显示load加载
            loading:function (config = {}) {
                this.layer.load(config);
            },
            //关闭加载
            closeLoading:function () {
                this.layer.closeAll('loading');
            },
            msg:function (info,option,callback) {
                this.layer.msg(info,option,callback);
            }
        }
        //静态方法
        LayUtil.layer = new Inner();
    })(LayUtil),
    //form 表单
    form:(function (LayUtil) {
        function Inner() {
        }
        Inner.prototype = {
            construct:Inner,
            init:function (callback) {
                let that = this;
                layui.use('form',function () {
                    that.form = layui.form;
                    that.form.render();
                    if(callback instanceof Function){
                        callback(that,that.form);
                    }
                })
                return this;
            },
            //验证
            verify:function (validator) {
                this.form.verify(validator)
            },
            //提交表单
            submit:function (callback,name,type="submit") {
                this.form.on(type+"("+(name===undefined?'form':name)+")",function (obj) {
                    if(callback instanceof Function){
                        callback(obj);
                        return false;
                    }
                    return true;
                })
            }
        }
        LayUtil.form = new Inner();
    })(LayUtil)

}