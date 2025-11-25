<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 2025/11/21
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加用户</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="UserServlet?op=add">

            <div class="form-group">
                <div class="label">
                    <label>用户名</label>
                </div>
                <div class="field">
                    <script src="js/laydate/laydate.js"></script>
                    <input type="text" class="laydate-icon input w50" name="username" data-validate="required:用户名不能为空"  style="padding:10px!important; height:auto!important;border:1px solid #ddd!important;" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>密码</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="password" data-validate="required:密码不能为空" value=""  />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>确认密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="repassword" value="" data-validate="required:请再次输入密码,repeat#password:两次输入的密码不一致"  />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body></html>
