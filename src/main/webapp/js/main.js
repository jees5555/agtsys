$(function(){
	//加载系统菜单
	$("#mm").menu({
		onClick : function (item){
			if(item.text=="退出系统"){
				window.location.href="user/logout";
			}else if (item.text=="修改密码"){
				showUpdatePassword();
			}
		}
	})
	//加载树形目录
	$('#tree').tree({
		url : "main/tree",
		onClick: function(node){
		   if(node.attributes.url!=""){
			//判断选项卡是否存在，存在则选中
			if($('#tabs').tabs('exists',node.text)){
				$('#tabs').tabs('select',node.text)
			}else{
			//不存在则打开
			createTab(node);
			}
		 }	
		}	
	});	
})

//加载修改密码页面
function showUpdatePassword() {
	$("#formbox").dialog({
		title : '修改密码',
		iconCls : "icon-ok",
		width : 300,
		height : 280,
		cache : false,
		href : 'user/updatePassword',
		modal : true,
		buttons:[{
			text:'修改',
			handler:function(){
				updatePassword();
			}
		},{
			text:'取消',
			handler:function(){
				$("#formbox").dialog('close');
			}
		}]
	})
}
//修改密码
function updatePassword() {
	if($("#formbox").form('validate')){
		//验证旧密码
		if(validateOldPwd($("#oldpassword").val())){
			//修改密码
			$.ajax({
				type: "POST",
				url : "user/doUpdatePassword",
				data : "newpassword="+$("#newpassword").val(),
				dataType : "text",
				async : false,
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

// 创建一个新的选项卡    
function createTab(node){
	$('#tabs').tabs('add',{    
	    title: node.text,    
	    //content : "<iframe src='role/page'></iframe>",
	    href : node.attributes.url,
	    closable:true,    
	    tools:[{    
	        iconCls:'icon-mini-refresh',    
	        handler:function(){    
	        	// 获取选择的面板
	        	var tab = $('#tabs').tabs('getSelected');  
	        	tab.panel('refresh');
 
	        }    
	    }]    
	});  
}