<#macro layout>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title></title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="${basePath}/admin/layui/css/layui.css" media="all">
        <link rel="stylesheet" href="${basePath}/admin/css/public.css" media="all">
        <link rel="stylesheet" href="${basePath}/admin/css/admin.css" media="all">
    </head>
    <body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <#nested >
    </div>
</body>
</html>
</#macro>