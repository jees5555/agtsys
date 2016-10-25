<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<table id="systemconfigdg${configtype}"></table> 
<script type="text/javascript">
$('#systemconfigdg${configtype}').datagrid({    
    url:'systemconfig/list/${configtype}',
    columns:[[       
        {field:'configtypename',title:'配置类型',width:200,align:'center'}, 
        //<c:if test="${configtype==2 ||configtype==3 ||configtype==4||configtype==7}">
        {field:'configvalue',title:'配置数值',width:300,align:'center',}, 
        //</c:if>
        //<c:if test="${configtype!=3 && configtype!=4}">
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
       //</c:if>
       {field:'id',title:'操作',width:100,align:'center',
           formatter: function(value,row,index){
        	   return "<a href='javascript:showUpdateSystemConfig("+value+")'>修改</a>"+
        	   "<c:if test="${configtype==1 ||configtype==5 ||configtype==6||configtype==7}">"+
        	   "|"+
        	   "<a href='javascript:deleteSystemConfig("+value+",\""+row.configtypename+"\");'>删除</a>"+
        	   "</c:if>";
         }}
    ]],
    onLoadError:function(){
		$.messager.alert('错误',"服务器异常，数据加载失败！",'error');
	},
    //表格属性
    fitColumns : true,
    singleSelect: true,
    rownumbers :true,
    //工具栏
    toolbar:[
     //<c:if test="${configtype !=3 && configtype !=4}">
       {
		iconCls: 'icon-add',
		text:'添加',
		handler: function(){
			showAddSystemConfig("${configtype}");
		        }
	    },
     //</c:if>
       {}
    ]
});  


//载入添加页面
function showAddSystemConfig(configtype){
	$("#formbox").dialog({
		title : '添加系统配置'+configtype,
		iconCls : "icon-add",
		width : 300,
		height : 220,
		cache : false,
		href : 'systemconfig/add/'+configtype,
		modal : true,
		buttons:[{
			text:'添加',
			handler:function(){
				addSystemConfig();
			}
		},{
			text:'取消',
			handler:function(){
				$("#formbox").dialog('close');
			}
		}],
	    onLoadError:function(){
			$.messager.alert('错误',"服务器异常，数据加载失败！",'error');
		}
	})
}
//载入修改页面
function showUpdateSystemConfig(id){
	 $("#formbox").dialog({
		title : '修改系统配置',
		iconCls : "icon-edit",
		width : 300,
		height : 220,
		cache : false,
		href : 'systemconfig/update/'+id,
		modal : true,
		buttons:[{
			text:'修改',
			handler:function(){
				updateSystemConfig();
			}
		},{
			text:'取消',
			handler:function(){
				$("#formbox").dialog('close');
			}
		}],
	    onLoadError:function(){
			$.messager.alert('错误',"服务器异常，数据加载失败！",'error');
		}
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
	    		},
				error : function() {
					$.messager.alert('错误','服务器异常！','error');
				}
	    	})  
	    }    
	});   
}
 

</script>