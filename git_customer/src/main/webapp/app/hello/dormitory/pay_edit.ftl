<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="icon" href="/favicon.ico">
    <title>费用编辑</title>
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
            <h2 class="sub-header">费用编辑</h2>

            <form class="form-horizontal" action="/pay/saveOrUpdate" method="post">
                <input type="hidden" name="pay.id" value="${(pay.id)!}"/>

                <div class="form-group">
                    <label for="flag" class="col-sm-2 control-label">类型</label>

                    <div class="col-sm-10">
                        <select class="form-control" value="${(pay.flag)!}" name="pay.flag" id="flag">
                            <option value="1">缴费</option>
                            <option value="2">开销</option>
                            <option value="3">住宿费（5元/天）</option>
                        </select>${flagMsg!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="time" class="col-sm-2 control-label">时间</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${(pay.time)!}" id="time"
                               name="pay.time">${timeMsg!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="amount" class="col-sm-2 control-label">金额</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${(pay.amount)!}" id="amount"
                               name="pay.amount">${amountMsg!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="user_id" class="col-sm-2 control-label">用户</label>

                    <div class="col-sm-10">
                        <select class="form-control" value="${(pay.user_id)!}" name="pay.user_id" id="user_id">
                        <#list userPage.getList() as user>
                            <option value="${user.id}">${user.name}</option>
                        </#list>
                        </select>${user_idMsg!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="remark" class="col-sm-2 control-label">备注</label>

                    <div class="col-sm-10">
                        <textarea class="form-control" id="remark" name="pay.remark"
                                  rows="3"><#if pay??>${pay.remark}</#if></textarea>${remarkMsg!}
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">编辑</button>
                    </div>
                </div>
            </form>
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