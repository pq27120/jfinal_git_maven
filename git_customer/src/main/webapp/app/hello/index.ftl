<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="icon" href="/favicon.ico">
    <title>bootstrap demo</title>
    <link href="/modules/bootstrap/1.0.0/bootstrap.css" rel="stylesheet">
    <link href="/static/hello/src/dashboard.css" rel="stylesheet">
</head>
<body>

<#include "common/banner.html"/>
<@banner>
</@banner>

<div class="container-fluid">
    <div class="row">

        <#include "common/left_menu.html"/>
        <@menu>
        </@menu>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">标题</h2>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>时间</th>
                        <th>内容</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list deferPage.getList() as defer>
                        <tr>
                            <td>${defer.id}</td>
                            <td>${defer.time}</td>
                            <td>${defer.remark}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
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

    seajs.use(["/static/hello/src/main"]);
</script>
</body>
</html>