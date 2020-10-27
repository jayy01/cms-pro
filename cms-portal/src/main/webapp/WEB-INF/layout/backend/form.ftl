<#--通用form模板-->
<#macro form style="">
    <form class="layui-form" method="post" style="${style}">
        <#nested >
    </form>
</#macro>
<#--form表单中的一行-->
<#macro item>
    <div class="layui-form-item">
        <#nested >
    </div>
</#macro>
<#--form表单中的一列-->
<#macro inline lable="" class="">
    <div class="layui-inline ${class}" >
        <label class="layui-form-label">${lable}</label>
        <div class="layui-input-inline">
            <#nested >
        </div>
    </div>
</#macro>
<#--form中一行一列-->
<#macro aline lable="">
    <label class="layui-form-label">${lable}</label>
    <div class="layui-input-block">
        <#nested >
    </div>
</#macro>
<#--提交重置按钮-->
<#macro submit>
    <div class="layui-input-block">
        <button type="button" class="layui-btn" lay-submit lay-filter="form">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</#macro>
