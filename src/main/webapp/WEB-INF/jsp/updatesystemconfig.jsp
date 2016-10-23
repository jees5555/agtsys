<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="updateSystemConfig" action="systemconfig/update" method="post" style="padding-left:30px;padding-top: 15px"> 
   <div id="errors" class="h30 lh28">
   </div>
   <input id="id" type="hidden" name="id" value="${systemconfig.id }"/>
   <input id="configtype" name="configtype" type="hidden" value="${systemconfig.configtype }">
    <div class="h30 lh28">   
        <label for="configtypename">配置类型:</label>   
        <c:choose>
          <c:when test="${systemconfig.configtype==3 or systemconfig.configtype==4}">
           <input id="configtypename" type="hidden" name="configtypename" value="${systemconfig.configtypename }"/> 
           ${systemconfig.configtypename }
          </c:when>
          <c:otherwise>
          <input id="configtypename" class="easyui-validatebox" type="text" name="configtypename" value="${systemconfig.configtypename }"
           onblur="validateConfigTypeName(${systemconfig.configtype},this.value);"
           data-options="required:true" missingMessage="请输入配置类型"/>   
          </c:otherwise>
        </c:choose>
    </div> 
   <c:if test="${systemconfig.configtype==2 or  systemconfig.configtype==3 or systemconfig.configtype==4 or systemconfig.configtype==7}">
    <div class="h30 lh28">   
        <label for="configvalue">配置数值:</label>   
        <input id="configvalue" class="easyui-validatebox" type="text" name="configvalue" value="${systemconfig.configvalue }"
        data-options="required:true" missingMessage="请输入配置数值"/>   
    </div> 
    </c:if>
    <c:if test="${systemconfig.configtype!=3 and systemconfig.configtype!=4 }">
    <div class="h30 lh28">   
       <label for="isstart">是否启用:</label>
       <select id="isstart" name="isstart">
       <option value="1" ${systemconfig.isstart==1?"selected='selected'":""}>启用</option>
       <option value="0" ${systemconfig.isstart==0?"selected='selected'":""}>不启用</option>
       </select>
    </div> 
    </c:if>  
</form>
<script type="text/javascript">

//验证配置类型名称
function validateConfigTypeName(configtype,configtypename){
	var oldconfigtypename ="${systemconfig.configtypename}";
	if(configtypename==""){
		$("#errors").html("");
		return;
	}
	if(configtypename==oldconfigtypename){
		$("#errors").html("");
		return true;
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
		}
	}) 
	return flag;
}

//修改系统配置
function updateSystemConfig() {
	if($("#formbox").form('validate')){
		//验证配置名称是否存在
		if(validateConfigTypeName('${systemconfig.configtype}',$("#configtypename").val())){
			//添加系统配置
		$("#updateSystemConfig").form('submit',{
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('修改提示','修改配置类型['+$("#configtypename").val()+']成功！','info')
						$("#formbox").dialog('close');
						$('#systemconfigdg${systemconfig.configtype}').datagrid('reload');
					}else{
						$.messager.alert('修改提示','修改配置类型['+$("#configtypename").val()+']失败！','error')
					}
				}
			}) 
		}	
		
	}
}
</script>