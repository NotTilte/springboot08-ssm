<%@page pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
<h2>显示登录用户信息:&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">登出</a></h2>
${sessionScope.validUser.id}&nbsp;&nbsp;
${sessionScope.validUser.email}&nbsp;&nbsp;
${sessionScope.validUser.nickname}&nbsp;&nbsp;
${sessionScope.validUser.password}&nbsp;&nbsp;
${sessionScope.validUser.userIntegral}&nbsp;&nbsp;
${sessionScope.validUser.isEmailVerify}&nbsp;&nbsp;
${sessionScope.validUser.emailVerifyCode}&nbsp;&nbsp;
${sessionScope.validUser.lastLoginTime}&nbsp;&nbsp;
${sessionScope.validUser.lastLonginIp}&nbsp;&nbsp;
</body>
</html>