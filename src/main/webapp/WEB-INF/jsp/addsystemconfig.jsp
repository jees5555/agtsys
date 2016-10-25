<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="addSystemConfig" action="systemconfig/add" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
   <input id="configtype" name="configtype" type="hidden" value="${configtype }">
    <div class="h30 lh28">   
        <label for="configtypename">配置类型:</label>   
        <input id="configtypename" class="easyui-validatebox" type="text" name="configtypename" onblur="validateConfigTypeName(${configtype},this.value);"
        data-options="required:true" missingMessage="请输入配置类型"/>   
    </div> 
   <c:if test="${configtype==2 or  configtype==7}">
    <div class="h30 lh28">   
        <label for="configvalue">配置数值:</label>   
        <input id="configvalue" class="easyui-validatebox" type="text" name="configvalue"
        data-options="required:true" missingMessage="请输入配置数值"/>   
    </div> 
    </c:if>
    <div class="h30 lh28">   
       <label for="isstart">是否启用:</label>
       <select id="isstart" name="isstart">
       <option value="1" selected="selected">启用</option>
       <option value="0">不启用</option>
       </select>
    </div>   
</form>
<script type="text/javascript">

//验证配置类型名称
function validateConfigTypeName(configtype,configtypename){
	if(configtypename==""){
		$("#errors").html("");
		return;
	}
	var flag =false;
	$.ajax({
		type: "POST",
		url : "systemconfig/configtypenameCheck",
		data : "configtype="+configtype+"&configtypename="+configtypename,
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg=="success"){
				$("#errors").html("<p style='color: green'>配置类型["+$('#configtypename').val()+"]可以使用</p>");
				flag =true;
			}else{
				$("#errors").html("<p style='color: red'>配置类型["+$('#configtypename').val()+"]已经存在</p>");
			}
		},
		error : function() {
			$("#errors").html("<p style='color: red'>服务器异常，数据校验失败</p>");
		}
	})
	return flag;
}

//添加系统配置
function addSystemConfig() {
	if($("#formbox").form('validate')){
		//验证配置名称是否存在
		if(validateConfigTypeName('${configtype}',$("#configtypename").val())){
			//添加系统配置
		$("#addSystemConfig").form('submit',{
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('添加提示','添加配置类型['+$("#configtypename").val()+']成功！','info')
						$("#formbox").dialog('close');
						$('#systemconfigdg${configtype}').datagrid('reload');
					}else{
						$.messager.alert('添加提示','添加配置类型['+$("#configtypename").val()+']失败！','error')
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