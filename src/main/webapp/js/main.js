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
								alert('ok,submit')
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
})