<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="roledg"></table> 
<script type="text/javascript">
$('#roledg').datagrid({    
    url:'role/list',
    columns:[[
        {field:'choose',checkbox:true},      
        {field:'id',title:'id',width:100,hidden:true},        
        {field:'rolename',title:'角色名称',width:100,align:'center'},    
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
       }
    ]],
    //表格属性
    singleSelect: true,   
    //工具栏
    toolbar: [{
		iconCls: 'icon-add',
		text:'添加',
		handler: function(){
			showAddRole();
		}
	},'-',{
		iconCls: 'icon-edit',
		text:'修改',
		handler: function(){
			var row =$("#roledg").datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择你要修改的角色','warning');
			}else{
			showUpdateRole(row);
			}
		}
	},'-',{
		iconCls: 'icon-remove',
		text:'删除',
		handler: function(){
			var row =$("#roledg").datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择删除的角色','warning');
			}else{
				$.messager.confirm('确认','您确认想要删除角色['+row.rolename+']吗？',function(r){    
				    if (r){    
				    	deleteRole(row);
				    }    
				});  
			}
		}
	}]

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
//载入添加页面
function showAddRole(){
	$("#formbox").dialog({
		title : '添加角色',
		iconCls : "icon-ok",
		width : 300,
		height : 220,
		cache : false,
		href : 'role/add/',
		modal : true,
		buttons:[{
			text:'添加',
			handler:function(){
				addRole();
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
function showUpdateRole(row){
	$("#formbox").dialog({
		title : '修改角色',
		iconCls : "icon-ok",
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
//删除角色
function deleteRole(row) {
	$.ajax({
		type: "post",
		url : "role/delete/",
		data : "id="+row.id,
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg=="success"){
				$("#roledg").datagrid('reload');
				$.messager.alert('删除提示','删除角色['+row.rolename+']成功','info');
			}else{
				$.messager.alert('删除提示','删除角色['+row.rolename+']失败','error');
			}
		}
	})
}


</script>