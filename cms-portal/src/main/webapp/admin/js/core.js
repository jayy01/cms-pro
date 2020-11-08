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
        let opt = {load:true,autoComplete:true,goBack:true},loadHandler,loadTime, options = {
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
                    },time)
                }
                let that = this,handler;
                setTimeout(function () {
                    switch (res.restCode) {
                        case CONSTANT.HTTP.SUCCESS:
                            if(that.goBack){
                                handler = function () {
                                    // 自动返回上一页
                                    window.location.href = document.referrer;
                                }
                            }
                            if(that.autoComplete){
                                core.prompt.msg(res.info,{icon: 1,time:1000},handler)
                            }
                            break;
                        case CONSTANT.HTTP.ERROR:
                            core.prompt.msg(res.info,{icon: 5,time:1000})
                            break;
                    }
                },100);


                //处理自定义回调
                (callback instanceof Function) && callback(res)
            },
            error:function (XMLHttpRequest, textStatus, errorThrow) {
                loadHandler.closeLoading();
                core.prompt.msg("系统异常，请重试！",{icon: 5,time:1000},null);
            }
        }
        Object.assign(opt,options,option);// 自己传进来的参数覆盖默认的 赋值于一个新的对象 es6语法
        this.cancel = $.ajax(opt);
    },
    //提示相关
    prompt:{
        // 提示框
        msg:function (info, option, callback) {
            LayUtil.layer.init(function (inner) {
                inner.msg(info,option,callback)
            })
        },
        // 确认框
        confirm:function (info, option, callback) {
            LayUtil.layer.init(function (inner) {
                inner.confirm(info,option,callback);
            })
        }
    },
    // 业务相关
    bussiness:{
        // 删除
        delete:function (data,callback) {
            let config = {
                url:"delete.do",
                goBack: false,
                data:{id:data.id}
            };
            core.prompt.confirm("确认执行该操作？",{icon:3,title:"提示"},function () {
                core.http(config,callback);
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
//树形表格的默认属性
LayUtil.treeTableOption = {
    treeColIndex: 1,
    treeSpid: 0,
    treeIdName: 'id',
    treePidName: 'parentId',
    elem: '#treeTable',
    page: false
}
// 下拉树形结构默认属性
LayUtil.selectTreeOption = {
    elem: "#selectTree",
    dataType: "json",
    async: false,
    method: 'post',
    ficon:["1","-1"],
    dataStyle:"layuiStyle",
    initLevel: 1,
    width: "100%",
    dot: false,
    checkbar: false,
    formatter:{
        title: function (data) {
            let s = data.name;
            if(data.children){
                s += '<span style=\'color:blue\'>(' + data.children.length + ')</span>';
            }
            return s;
        }
    },
    response:{
        statusName:"restCode",
        statusCode:200,
        message: "restCode",
        rootName: "data",
        treeId: "id",
        parentId: "parentId",
        title: "name"
    }
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
            // 提示框
            msg:function (info,option,callback) {
                this.layer.msg(info,option,callback);
            },
            // 确认框
            confirm:function (info, option, callback) {
                let that = this;
                this.layer.confirm(info,option,function (index) {
                    that.layer.close(index);
                    (callback instanceof Function) && callback();
                });
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
    })(LayUtil),
    // treeTable
    treeTable:(function (LayUtil) {
        function Inner() {

        }
        Inner.prototype = {
            construct:Inner,
            init:function (config,callback) {
                let that = this,option = $.extend({},LayUtil.treeTableOption,config);
                layui.extend({
                    treetable: '{/}'+ BASE_PATH + '/admin/js/lay-module/treetable-lay/treetable'
                }).use(['treetable','table'],function () {
                    that.treetable = layui.treetable;
                    that.table = layui.table;
                    that.treetable.render(option);
                    // 渲染完成后监听右侧工具栏
                    that.rightTool(function (obj) {
                        if(obj.event !== undefined && obj.event === "del"){
                            // option 重新获取配置参数
                            that.delete(obj.data,$.extend({},LayUtil.treeTableOption,config));
                        }
                    });
                    (callback instanceof Function ) && callback(that,that.treetable,that.table);
                });
                return this;
            },
            //右侧工具栏
            rightTool: function (callback,filter='treeTable') {
                this.table.on('tool('+ filter +')',function (obj) {
                    (callback instanceof Function ) && callback(obj);
                })
            },
            // 表格单条删除操作
            delete:function (data,option) {
                let that = this;
                core.bussiness.delete(data,function () {
                    that.treetable.render(option);
                })
            }
        }
        LayUtil.treeTable = new Inner();
    })(LayUtil),
    // dtree 下拉树形
    selectTree:(function (LayUtil) {
        function Inner(){}
        Inner.prototype = {
            construct:Inner,
            init: function (config, callback) {
                let that = this,option = $.extend({},LayUtil.selectTreeOption,config);
                layui.extend({
                    dtree: '{/}' + BASE_PATH + "/admin/layui/layui_ext/dtree/dtree"
                }).use('dtree', function(){
                    that.dtree = layui.dtree;
                    that.dtree.renderSelect(option);
                    (callback instanceof Function) && callback(that,that.dtree);
                });
                return this;
            }
        }
        LayUtil.selectTree = new Inner();
    })(LayUtil)

}