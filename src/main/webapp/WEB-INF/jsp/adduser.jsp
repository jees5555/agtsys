<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="addUser" action="user/add" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
    <div class="h30 lh28">   
        <label for="usercode">登陆账号:</label>   
        <input id="usercode" class="easyui-validatebox" type="text" name="usercode" onblur="validateUserCode(this.value);"
        data-options="required:true" missingMessage="请输入登陆账号"/>   
    </div>
     <div class="h30 lh28">   
        <label for="username">用户名称:</label>   
        <input id="username" class="easyui-validatebox" type="text" name="username" 
        data-options="required:true" missingMessage="请输入用户名称"/>   
    </div> 
    <div class="h30 lh28">   
        <label for="userpassword">登录密码:</label>   
        <input id="userpassword" class="easyui-validatebox" type="password" name="userpassword" value="123456"
        data-options="required:true" missingMessage="请输入登录密码"/> 
    </div> 
     <div class="h30 lh28"> <label for="defaultpassword"><font color="red">默认初始密码123456</font></label></div>
     <div class="h30 lh28">   
       <label for="roleid">&ensp;角&emsp;色&ensp;:</label>   
       <select id="roleid" class="easyui-validatebox" name="roleid" data-options="required:true" missingMessage="请选择角色">
       <option selected="selected" value="">请选择</option>
       <c:forEach items="${roles }" var="role">
       <option  value="${role.id }">${role.rolename }</option>
       </c:forEach>
       </select>
    </div> 
    <div class="h30 lh28">   
       <label for="isstart">是否启用:</label>
       <select id="isstart"  name="isstart">
       <option value="1" selected="selected">启用</option>
       <option value="0">不启用</option>
       </select>
    </div>   
</form>
<script type="text/javascript">
//验证登录账号
function validateUserCode(usercode){
	if(usercode==""){
		$("#errors").html("");
		return;
	}
	var flag =false;
	$.ajax({
		type: "POST",
		url : "user/usercodeCheck",
		data : "usercode="+usercode,
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg=="success"){
				$("#errors").html("<p style='color: green'>用户["+$('#usercode').val()+"]可以使用</p>");
				flag =true;
			}else{
				$("#errors").html("<p style='color: red'>用户["+$('#usercode').val()+"]已经存在</p>");
			}
		},
		error : function() {
			$("#errors").html("<p style='color: red'>服务器异常，数据校验失败</p>");
		}
	})
	return flag;
}

//添加用户
function addUser() {
	if($("#formbox").form('validate')){
		//验证用户账户是否存在
		if(validateUserCode($("#usercode").val())){
			//添加角色
			$("#addUser").form('submit',{
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('添加提示','添加用户['+$("#usercode").val()+']成功！','info')
						$("#formbox").dialog('close');
						$("#userdg").datagrid('reload');
					}else{
						$.messager.alert('添加提示','添加用户['+$("#usercode").val()+']失败！','error')
					}
				},
				error : function() {
					$.messager.alert('错误','服务器异常！','error');
				}
			})
		}	
		
	}
}
</script>