$(function(){
	$("#mm").menu({
		onClick : function (item){
			if(item.text=="退出系统"){
				window.location.href="logout";
			}else if (item.text=="修改密码"){
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
					},{
						text:'取消',
						handler:function(){
							$("#formbox").dialog('close');
						}
					}]
				})
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

// 创建一个新的选项卡    
function createTab(node){
	$('#tabs').tabs('add',{    
	    title: node.text,    
	    content: node.text,    
	    closable:true,    
	    tools:[{    
	        iconCls:'icon-mini-refresh',    
	        handler:function(){    
	            alert('refresh');    
	        }    
	    }]    
	});  
}