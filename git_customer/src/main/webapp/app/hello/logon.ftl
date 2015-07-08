<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link href="/modules/bootstrap/1.0.0/bootstrap.css" rel="stylesheet">
    <title>登录</title>
</head>
<body>

<pre>
  &lt;p&gt;Sample text here...&lt;/p&gt;
</pre>

<!-- Button to trigger modal -->
<a href="#myModal" role="button" class="btn" data-toggle="modal">显示对话框</a>

<!-- Modal -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Modal header</h3>
    </div>
    <div class="modal-body">
        <p>One fine body…</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
        <button class="btn btn-primary">Save changes</button>
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

    seajs.use(["/static/hello/src/logon"]);
</script>
</body>
</html>