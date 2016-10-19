<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="updatePassword" action="user/doUpdatePassword" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
    <div class="h30 lh28">   
        <label for="oldpassword">旧&ensp;密&ensp;码:</label>   
        <input id="oldpassword" class="easyui-validatebox" type="password" name="userpassword" data-options="required:true" missingMessage="请输入旧密码"/>   
    </div>   
    <div class="h30 lh28">   
        <label for="newpassword">新&ensp;密&ensp;码:</label>   
        <input id="newpassword" class="easyui-validatebox" type="password" name="newpassword" data-options="required:true" missingMessage="请输入新密码"/>   
    </div>
    <div class="h30 lh28">   
        <label for="newpassword2">确认密码:</label>   
        <input id="newpassword2" class="easyui-validatebox" type="password" name="newpassword2" data-options="required:true" missingMessage="请再次输入密码" 
        validType="passwordEquals['#newpassword']"/>
    </div>     
</form>  
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {    
	passwordEquals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入的密码不一致'   
    }    
});  

function validateOldPwd(oldpassword){
	if(oldpassword==""){
		$("#errors").html("");
		return;
	}
	var flag =false;
	$.ajax({
		type: "POST",
		url : "user/passwordCheck",
		data : "oldpassword="+oldpassword,
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg=="success"){
				$("#errors").html("<p style='color: green'>旧密码正确</p>");
				flag =true;
			}else{
				$("#errors").html("<p style='color: red'>旧密码错误</p>");
			}
		}
	})
	return flag;
}
//修改密码
function updatePassword() {
	if($("#formbox").form('validate')){
		//验证旧密码
		if(validateOldPwd($("#oldpassword").val())){
			//修改密码
			$("#updatePassword").form('submit',{
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('修改提示','修改密码成功！','info')
						$("#formbox").dialog('close');
					}else{
						$.messager.alert('修改提示','修改密码失败！','error')
					}
				}
			})
		}	
		
	}
}
//自动验证旧密码
$("#oldpassword").blur(function(){
	validateOldPwd($(this).val());
})
</script>