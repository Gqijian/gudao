<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台</title>
    <link href="/gudao/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="/gudao/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="login_m">
    <div class="login_logo"><img src="/gudao/img/Logo1.png" ></div>
    <div class="login_boder">
    <form id="loginForm" action="/gudao/seller/login.action" method="post">
        <div class="login_padding" id="login_model">

            <h2>账号</h2>
            <label>
                <input type="text" name="realName" id="realName" placeholder="输入用户名" class="txt_input txt_input2">
            </label>
            <h2>密码</h2>
            <label>
                <input type="password" id="password" name="password"  placeholder="输入密码" class="txt_input">
            </label>
            <p id="error"></p>
            <div class="rem_sub">
                <label>
                    <input type="submit" class="sub_button" name="Submit" id="Submit" value="登录" style="opacity: 0.7;">
                </label>
            </div>
        </div>
    </form>

        <div class="copyrights"></div>
</body></html>