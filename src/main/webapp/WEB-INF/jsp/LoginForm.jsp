<%@page pageEncoding="utf-8" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">


    <script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>

    <script type="text/javascript">

        $(function () {
            //记录服务器端验证码的校验状态
            var codeVerified=false;

            //图像点击事件
            $("#imgCode").click(function () {
                $("#imgCode").attr("src","${pageContext.request.contextPath}/image/validatecode?time="+(new Date().getTime()) )
            });

            //验证码表单项失去焦点时间
            $("#codeInput").blur(function () {
                var codeValue=$("#codeInput").val();

                //ajx校验
                $.get("verify?code="+codeValue,function (data) {
                    /*经测试：
                        如果服务器反馈回来的是Boolean类型的值，则data就是boolea类型的值
                        如果服务器反馈回来的是string类型的“true”，则data就是string类型；*/
                    if(data){
                        codeValue=true;
                        $("#errorArea").html("验证码正确！");
                    }else {
                        codeValue=false;
                        $("#errorArea").html("验证码不正确！");
                    }
                })
            })


            //表单提交事件
            $("#fm").submit(function () {
                //首先实施验证码的非空校验
                var codeRequired=false;//记录验证码的必须填性状态
                var codeValue=$("#codeInput").val();
                if(codeValue.length!=0){
                    //验证码是否填写
                    codeRequired=true;
                }else {
                    codeRequired=false;
                    $("#errorArea").html("验证码必须填写！");
                }
                //将验证码的非空性与正确性结合
                return codeRequired&&codeVerified;
            });
        });




    </script>

</head>

<body>

    <h2>填写登录信息</h2>
    <%--@elvariable id="user" type=""--%>
    <form:form modelAttribute="user" action="login" method="post" id="fm">
        <div id="errorArea" class="redColor" style="font-size: 30px">
            <%--用户或密码错误--%>
            ${errorMessage}
        </div>
        <p>
            <div><form:errors path="email" cssClass="redColor"/></div>
            用户邮箱：<form:input path="email"/>
        </p>
        
        <p>
            <div><form:errors path="password" cssClass="redColor"/> </div>
            用户密码：<form:input path="password"/>
        </p>
        <p>
            <label>验证码</label>
            <input type="text" id="codeInput" name="code">
            <img  id="imgCode" src="${pageContext.request.contextPath}/image/validatecode"
                width="100px" height="30px" style="vertical-align: bottom"/>
        </p>

        <p>
            <input type="reset" value="重置">
            <input type="submit" value="提交">
        </p>
    </form:form>
</body>




</html>