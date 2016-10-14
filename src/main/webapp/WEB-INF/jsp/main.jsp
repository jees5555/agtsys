<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代理商管理系统</title>
<%@ include file="head.html" %>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript">
$(function(){
	$('#tree').tree({
		data: [{
			text: '父菜单1',
			state: 'closed',
			children: [{
				text: '子菜单1'
			},{
				text: '子菜单2'
			}]
		},{
			text: '父菜单2'
		}],
		onClick: function(node){
			//判断选项卡是否存在，存在则选中
			if($('#tabs').tabs('exists',node.text)){
				$('#tabs').tabs('select',node.text)
			}else{
			//不存在则打开
			createTab(node);
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
</script>
</head>
<body class="easyui-layout">  
    <!-- 页面头部 -->  
    <div data-options="region:'north'" 
    style="height:120px; background: url('images/top_1px.gif') repeat-x">
    <!-- logo背景图片 -->
    <div style="height:118px; background: url('images/top_bg.gif') no-repeat;">
    <!-- 系统菜单 -->
       <div style="float: right;">
       <a href="javascript:void(0)" id="mb" class="easyui-menubutton"     
        data-options="menu:'#mm',iconCls:'icon-more'">系统菜单</a>   
            <div id="mm" style="width:150px;">   
            <div data-options="iconCls:'icon-edit'">修改密码</div>  
            <div class="menu-sep"></div>   
            <div data-options="iconCls:'icon-cancel'">退出系统</div>   
            </div>  
       </div>
       <div style="height:100% ;line-height:110px ;margin-left: 600px">欢迎你，${user.username}</div>
    </div>
    </div> 
    <!-- 页面底部 -->  
    <div data-options="region:'south'" style="height:50px;">
      <!-- 版权声明 -->
      <div style="text-align: center; line-height: 48px">Copyright © 2016 jym 不保留权利 </div>
    </div>
    <!-- 去掉右边的区域 -->   
    <!--<div data-options="region:'east',iconCls:'icon-reload',split:true" style="width:100px;"></div>   -->
    <!-- 左边区域 -->  
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;">
     <!-- 树形功能菜单 -->  
	     <ul id="tree"></ul>  
    </div >   
      <div data-options="region:'center'" style="padding:5px;background:#eee;">
         <div id="tabs" class="easyui-tabs" style="width:500px;height:250px;" fit="true"></div>  
    </div>
    <!-- 公用div -->   
    <div id="formbox"></div>
</body>  

</html>