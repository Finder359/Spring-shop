<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 2025/11/21
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>修改用户</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 修改用户</strong></div>
    <div class="body-content">

        <!-- 注意：这里的 action 是 update -->
        <form method="post" class="form-x" action="UserServlet?op=update">

            <!-- 隐藏字段：用户 ID -->
            <input type="hidden" name="id" value="${user.id}" />

            <!-- 用户名 -->
            <div class="form-group">
                <div class="label">
                    <label>用户名</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50"
                           name="username"
                           value="${user.username}"
                           data-validate="required:用户名不能为空" />
                    <div class="tips"></div>
                </div>
            </div>

            <!-- 密码 -->
            <div class="form-group">
                <div class="label">
                    <label>密码</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50"
                           name="password"
                           value="${user.password}"
                           data-validate="required:密码不能为空" />
                    <div class="tips"></div>
                </div>
            </div>

            <!-- 确认密码 -->
            <div class="form-group">
                <div class="label">
                    <label>确认密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50"
                           name="repassword"
                           value="${user.password}"
                           data-validate="required:请再次输入密码,repeat#password:两次输入的密码不一致" />
                    <div class="tips"></div>
                </div>
            </div>

            <!-- 提交按钮 -->
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 保存修改</button>
                </div>
            </div>

        </form>
    </div>
</div>

</body>
</html>
