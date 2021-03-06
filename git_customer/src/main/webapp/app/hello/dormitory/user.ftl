<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="icon" href="/favicon.ico">
    <title>用户列表</title>
    <link href="/modules/bootstrap/1.0.0/bootstrap.css" rel="stylesheet">
    <link href="/static/hello/src/dashboard.css" rel="stylesheet">
</head>
<body>

<#include "../common/banner.ftl"/>
<@banner>
</@banner>

<div class="container-fluid">
    <div class="row">

    <#include "../common/left_menu.ftl"/>
    <@menu>
    </@menu>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">用户列表</h2>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>用户名</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list userPage.getList() as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.time}</td>
                        <td>
                            <a href="/user/edit/${user.id}">编辑</a>
                            <a href="/user/del/${user.id}">删除</a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <div>
                <a href="/user/add" class="btn btn-primary btn-sm">新增</a>
            </div>
        </div>
    </div>
</div>

<script src="/modules/seajs/3.0.0/sea.js"></script>
<script>
    // Set configuration
    seajs.config({
        base: "/modules/",
        alias: {
            "jquery": "jquery/1.10.1/jquery.js",
            "bootstrap": "bootstrap/1.0.0/bootstrap.js"
        }
    });

    //    seajs.use(["/static/hello/dormitory/src/user"]);
</script>
</body>
</html>