<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="addRole" action="#" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
    <div class="h30 lh28">   
        <label for="rolename">角色名称:</label>   
        <input id="rolename" class="easyui-validatebox" type="text" name="rolename" data-options="required:true" missingMessage="请输入角色名称"/>   
    </div>   
    <div class="h30 lh28">   
       <label for="isstart">是否启用:</label>
       <select id="isstart" name="isstart">
       <option value="1" selected="selected">启用</option>
       <option value="0">不启用</option>
       </select>
    </div>   
</form>
<script type="text/javascript">

//验证角色名
function validateRoleName(rolename){
	if(rolename==""){
		$("#errors").html("");
		return;
	}
	var flag =false;
	$.ajax({
		type: "POST",
		url : "role/rolenameCheck",
		data : "rolename="+rolename,
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg=="success"){
				$("#errors").html("<p style='color: green'>角色名称可以使用</p>");
				flag =true;
			}else{
				$("#errors").html("<p style='color: red'>角色名称已经存在</p>");
			}
		}
	})
	return flag;
}

$("#rolename").blur(function(){
	validateRoleName($(this).val());
})
</script>