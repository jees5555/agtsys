<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="updateUser" action="user/update" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
   <input id="id" name="id" type="hidden" value="${user.id }">
    <div class="h30 lh28">   
        <label for="usercode">登陆账号:</label>   
        <input id="usercode" class="easyui-validatebox" type="text" name="usercode" value="${user.usercode }"
        onblur="validateUserCode(this.value);"
        data-options="required:true" missingMessage="请输入登陆账号"/>   
    </div>
     <div class="h30 lh28">   
        <label for="username">用户名称:</label>   
        <input id="username" class="easyui-validatebox" type="text" name="username"  value="${user.username }"
        data-options="required:true" missingMessage="请输入用户名称"/>   
    </div> 
    <div class="h30 lh28">   
        <label for="userpassword">登录密码:</label>   
        <input id="userpassword" class="easyui-validatebox" type="password" name="userpassword" /> 
    </div>
     <div class="h30 lh28"> <label for="defaultpassword"><font color="red">无需修改密码可不填</font></label></div>
     <div class="h30 lh28">   
       <label for="roleid">&ensp;角&emsp;色&ensp;:</label>   
       <select id="roleid"  name="roleid">
       <option value="">请选择</option>
       <c:forEach items="${roles }" var="role">
       <option ${user.roleid==role.id?"selected='selected'":"" }  value="${role.id }">${role.rolename }</option>
       </c:forEach>
       </select>
    </div> 
    <div class="h30 lh28">   
       <label for="isstart">是否启用:</label>
       <select id="isstart"  name="isstart">
       <option ${user.isstart==1?"selected='selected'":"" } value="1" selected="selected">启用</option>
       <option ${user.isstart==0?"selected='selected'":"" } value="0">不启用</option>
       </select>
    </div>   
</form>
<script type="text/javascript">
//验证登录账号
function validateUserCode(usercode){
	var oldUserCode="${user.usercode }";
	if(usercode==""){
		$("#errors").html("");
		return;
	}
	if(usercode==oldUserCode){
		$("#errors").html("");
		return true;
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
		}
	})
	return flag;
}

//修改用户
function updateUser() {
	if($("#formbox").form('validate')){
		if($("#roleid").val()==""){
			$("#errors").html("<p style='color: red'>请选择角色</p>");
			return;
		}
		//验证用户账户是否存在
		if(validateUserCode($("#usercode").val())){
			//添加角色
			$("#updateUser").form('submit',{
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('修改提示','修改用户['+$("#usercode").val()+']成功！','info')
						$("#formbox").dialog('close');
						$("#userdg").datagrid('reload');
					}else{
						$.messager.alert('修改提示','修改用户['+$("#usercode").val()+']失败！','error')
					}
				}
			})
		}	
		
	}
}
</script>