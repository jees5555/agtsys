<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="updateRole" action="role/update" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
   <input id="id" type="hidden" name="id" value="${role.id }"/>
    <div class="h30 lh28">   
        <label for="rolename">角色名称:</label>   
        <input id="rolename" class="easyui-validatebox" type="text" name="rolename" value="${role.rolename }" onblur="validateRoleName(this.value);" 
        data-options="required:true" missingMessage="请输入角色名称"/>   
    </div>   
    <div class="h30 lh28">   
       <label for="isstart">是否启用:</label>
       <select id="isstart" name="isstart">
       <option value="1" ${role.isstart==1?"selected='selected'":"" }>启用</option>
       <option value="0" ${role.isstart==0?"selected='selected'":"" }>不启用</option>
       </select>
    </div>   
</form>
<script type="text/javascript">
var oldRolename="${role.rolename}";
//验证角色名
function validateRoleName(rolename){
	if(rolename==""){
		$("#errors").html("");
		return;
	}
	if(rolename== oldRolename){
		$("#errors").html("");
		return true;
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
				$("#errors").html("<p style='color: green'>角色名称["+$('#rolename').val()+"]可以使用</p>");
				flag =true;
			}else{
				$("#errors").html("<p style='color: red'>角色名称["+$('#rolename').val()+"]已经存在</p>");
			}
		},
		error : function() {
			$("#errors").html("<p style='color: red'>服务器异常，数据校验失败</p>");
		}
	})
	return flag;
}

//添加角色
function updateRole() {
	if($("#formbox").form('validate')){
		//验证角色是否存在
		if(validateRoleName($("#rolename").val())){
			//添加角色
			$("#updateRole").form('submit',{
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('修改提示','修改角色['+$("#rolename").val()+']成功！','info')
						$("#formbox").dialog('close');
						$("#roledg").datagrid('reload');
					}else{
						$.messager.alert('修改提示','修改角色['+$("#rolename").val()+']失败！','error')
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