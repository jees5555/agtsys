<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="roledg"></table> 
<script type="text/javascript">
$('#roledg').datagrid({    
    url:'role/list',
    columns:[[
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
			
		}
	},'-',{
		iconCls: 'icon-remove',
		text:'删除',
		handler: function(){
			
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
		href : 'role/addRole',
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
//添加角色
function addRole() {
	if($("#formbox").form('validate')){
		//验证角色是否存在
		if(validateRoleName($("#rolename").val())){
			//添加角色
			$.ajax({
				type: "POST",
				url : "role/doAddRole",
				data : "rolename="+$("#rolename").val()+"&isstart="+$("#isstart").val(),
				dataType : "text",
				async : false,
				success : function(msg){
					if(msg=="success"){
						$.messager.alert('修改提示','添加角色成功！','info')
						$("#formbox").dialog('close');
						$("#roledg").datagrid('reload');
					}else{
						$.messager.alert('修改提示','添加角色失败！','error')
					}
				}
			})
			
		}	
		
	}
}
</script>