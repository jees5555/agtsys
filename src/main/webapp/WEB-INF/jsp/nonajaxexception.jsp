<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>错误</title>
<%@ include file="head.html" %>
</head>
<body>
<script type="text/javascript">
$(function () {
	$("#error-div").dialog({
		title : '错误',
		iconCls : "icon-cancel",
		width : 350,
		height : 200,
		closable: false,
		draggable : false,
		cache : false,
	})
})
</script>
<div id="error-div">
    <div class="h30 lh28"></div>
    <div align="center" class="h30 lh28">
    <font style="font-size: 20px;">原因：
	<span style="color:red">${exception}</span><br>
	</font>
	</div>
	<div align="center" class="h30 lh28">
	<font>
		<span id="second">3</span>秒后返回上一页
	</font>
	</div>
</div>	
	<script type="text/javascript">
		var second = document.getElementById("second").innerHTML;
		var my_interval = setInterval(function() {	
			second--;
			if (second>0) {
				document.getElementById("second").innerHTML=second;
			} else {
				window.history.back();
				clearInterval(my_interval);			
			}
		}, 1000);
	</script>
</body>
</html>
