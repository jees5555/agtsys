<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<!-- 静态导入 -->
<%@ include file="head.html" %>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body style="overflow: hidden;">
<div>
	<img  src=images/background.jpg align=left 
        onload="this.width=screen.availWidth;">
</div>
<div id="login-div">
<form id="login-form" action="user/login" method="post" style="padding-left:30px;padding-top: 15px"> 
<div id="errors" class="h30 lh28">
     <c:if test="${!empty captchaError }">
         <p style="color: red">${captchaError }</p>
     </c:if>
      <c:if test="${!empty loginError }">
         <p style="color: red">${loginError }</p>
     </c:if>
     <c:if test="${!empty errors }">
       <c:forEach items="${errors }" var="error">
          <p style="color: red">${error.defaultMessage }</p>
       </c:forEach>
     </c:if>
   </div>  
    <div class="h30 lh28">   
        <label for="usercode">用户名:</label>   
        <input class="easyui-validatebox" type="text" name="usercode" data-options="required:true" missingMessage="请输入用户名"/>   
    </div>   
    <div class="h20 lh28">   
        <label for="userpassword">密&emsp;码:</label>   
        <input class="easyui-validatebox" type="password" name="userpassword" data-options="required:true" missingMessage="请输入密码"/>   
    </div>
    <div class="h30 lh28">   
        <label for="captcha">验证码:</label>   
        <input id="captcha" maxlength="4" class="easyui-validatebox" type="text" name="captcha" data-options="required:true" missingMessage="请输入验证码"/>
        <img id="captchaImg" src="captcha/get">
    <a id="captchaButton" href="javascript:">换一张</a> 
    </div> 
       
</form>  
</div>

</body>
</html>