<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div id="p" class="easyui-panel" title="搜索"     
        style="width:auto;height:auto;padding:10px;background:#fafafa;"   
        data-options="iconCls:'icon-search',collapsible:true">   
    <form id="userSearchForm">
     <table class="searchTb" style="margin:auto;">
      <tr>
       <th>用户名称：</th>
       <td><input type="text" id="username" name="username" style="height:14px"></td>
       <th>角色：</th>
       <td><select id="roleid" name="roleid">
       <option selected="selected" value="">请选择</option>
       <c:forEach items="${roles }" var="role">
       <option  value="${role.id }">${role.rolename }</option>
       </c:forEach>
       </select></td>
       <th>是否启用：</th>
       <td><select id="isstart" name="isstart">
        <option selected="selected" value="">请选择</option>
        <option value="1">启用</option>
        <option value="0">未启用</option>
       </select></td>
       <td>&emsp;<a href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
      </tr>
      </table>
    </form> 
</div>  
   
<table id="userdg"></table> 
<script type="text/javascript">
$('#userdg').datagrid({    
    url:'user/list/',
    columns:[[       
        {field:'usercode',title:'登陆账号',width:200,align:'center'}, 
        {field:'username',title:'用户名称',width:200,align:'center',},
        {field:'rolename',title:'角色',width:100,align:'center',},
        {field:'creationtime',title:'创建时间',width:200,align:'center',
        formatter: function(value){return formatDate(value)}},
        {field:'isstart',title:'是否启用',width:100,align:'center',
        formatter: function(value){
        	switch(value){
        	case 0:
        		return "未启用";
        	case 1:
        		return "启用"
        	}
          },
          styler: function(value,row,index){
				if (value ==0){
					return 'background-color:#ffee00;color:red;';
				}
			}
       },
       {field:'id',title:'操作',width:100,align:'center',
           formatter: function(value,row,index){
        	   return "<a href='#'>修改</a>"+
        	   "|"+
        	   "<a href='javascript:deleteSystemConfig("+value+",\""+row.configtypename+"\");'>删除</a>";
         }}
    ]],
    //表格属性
    fitColumns : true,
	rownumbers : true,
	idField : 'id',
    singleSelect: true,
    pagination : true,
    pageSize : 5,
    pageList : [5,10,20,30,50],
    //工具栏
    toolbar:[
     //<c:if test="${configtype !=3 && configtype !=4}">
       {
		iconCls: 'icon-add',
		text:'添加',
		handler: function(){
			showAddSystemConfig("${configtype}");
		        }
	    }
     //</c:if>
    ]
});

//格式化时间
function formatDate(date){
	var mydate =new Date(date);
     var year =	mydate.getFullYear();
     var month= (mydate.getMonth()+1);
     var day = mydate.getDate();
     var hour = mydate.getHours();
     var minute =mydate.getMinutes();
     var second =mydate.getSeconds();
     
     return year+"-"+(month<10?"0"+month:month)+"-"+(day<10?"0"+day:day)+" "+(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
}
//组合查询
function searchUser() {
	var user = {"username":null,"roleid":null,"isstart":null};
	if($.trim($('#username').val())!=''){
		user.username = $.trim($('#username').val());
	}
	if($.trim($('#roleid').val())!=''){
		user.roleid = $.trim($('#roleid').val());
	}
	if($.trim($('#isstart').val())!=''){
		user.isstart = $.trim($('#isstart').val());
	}
	$("#userdg").datagrid('reload',user);
}

//载入添加页面
function showAddSystemConfig(configtype){
	$("#formbox").dialog({
		title : '添加系统配置'+configtype,
		iconCls : "icon-edit",
		width : 300,
		height : 220,
		cache : false,
		href : 'systemconfig/add/'+configtype,
		modal : true,
		buttons:[{
			text:'添加',
			handler:function(){
				addSystemConfig(configtype);
			}
		},{
			text:'取消',
			handler:function(){
				$("#formbox").dialog('close');
			}
		}]
	})
}
//载入修改页面
function showUpdateSystemConfig(id,configtype){
	$("#formbox").dialog({
		title : '修改角色',
		iconCls : "icon-edit",
		width : 300,
		height : 220,
		cache : false,
		href : 'role/update/'+row.id,
		modal : true,
		buttons:[{
			text:'修改',
			handler:function(){
				updateRole();
			}
		},{
			text:'取消',
			handler:function(){
				$("#formbox").dialog('close');
			}
		}]
	}) 
} 
//删除系统配置
function deleteSystemConfig(id,configtypename) {
	$.messager.confirm('确认','您确认想要删除配置类型['+configtypename+']吗？',function(r){    
	    if (r){    
	    	$.ajax({
	    		type: "post",
	    		url : "systemconfig/delete/",
	    		data : "id="+id,
	    		dataType : "text",
	    		async : false,
	    		success : function(msg){
	    			if(msg=="success"){
	    				$("#systemconfigdg${configtype}").datagrid('reload');
	    				$.messager.alert('删除提示','删除配置类型['+configtypename+']成功','info');
	    			}else{
	    				$.messager.alert('删除提示','删除配置类型['+configtypename+']失败','error');
	    			}
	    		}
	    	})  
	    }    
	});   
}
 

</script>