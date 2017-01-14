/**
 * 角色配置管理
 */
// 全局变量
var check_roleid = -1;
var check_rolename ="";
// 获取指定角色的功能权限
function getFunctionsByRoleId(roleid,rolename) {
	try{
	// 清除选中状态
	 $('#permissiondg').treegrid('clearChecked');
	}catch(err){
		
	}
	 getFunctionsByRoleIdPart2(roleid,rolename);
}

function getFunctionsByRoleIdPart2(roleid,rolename) {
	// 给全局变量赋值
	checkroleid = roleid;
	checkrolename = rolename;
	// 清空数据
	$('#permissiondg').treegrid({
		data : null
	});
	
	// 重新加载数据
	$("#permissiondg").treegrid({
		url : "permission/list/" + roleid,
		idField:'id',    
		treeField:'functionname',
		animate :true,
		checkbox: true,
		columns : [ [ 
		   {field : 'id',title : 'Id',hidden : true},
		   {field : 'functionname',title : '功能名称'}, 
		   {field : 'funcurl',title : '功能路径'},
		   {field : 'creationtime',title : '创建时间',
			formatter : function(value) {
				return formatDate(value);
			}
		}, 
		{field : 'createdby',title : '创建者'},
		{field : 'isstart',title : '是否启用',align : 'center',
			formatter : function(value, row, index) {
				switch (value) {
				case 0:
					return "停用";
					break;
				case 1:
					return "启用";
					break;
				default:
					return "-";
					break;
				}
			},
			styler : function(value, row, index) {
				if (value == 0) {
					return 'color:red;';
				} else {
					return 'color:green;';
				}
			}
		} ] ],
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				savePermission();
			}
		}, {
			text : '重置',
			iconCls : 'icon-undo',
			handler : function() {
				reset();
			}
		} ],
		fitColumns : true,// 自适应宽度
		striped : true,// 列表是否有间隔底色
		resizable :true,// 列尺寸可调节
		rownumbers : true, // 行前显示行号
		idField : 'id', // 指定选中时返回的维度字段名，如id

		singleSelect : false,

		selectOnCheck : true,

		checkOnSelect : true,
		
		onLoadError:function(error){
			$.messager.alert('错误','服务器异常，数据加载失败！','error');
		},
	   onLoadSuccess : function(data) { 
		   //$('#permissiondg').treegrid('clearChecked');
		}
	 });
}
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

function savePermission() {
	$.messager.confirm('确认', '你确定要修改['+checkrolename+']的功能权限吗?', function(r){
		if (r){
			//获取当前选中的functionid
			var flist = '';
			$.each($('#permissiondg').treegrid('getChecked'),function(index, item) {
				flist += item.id+',';
			});
			alert(flist)
			
			//请求服务器更新权限配置
			/*$.post("permission/save",{'flist':flist,'roleid':checkroleid},function(result){
				if("success" == result){
					$.messager.alert('提示','保存成功！','info');
				}else{
					$.messager.alert('提示','保存失败！','error');
				}
			},'html').error(function(){
				$.messager.alert('错误','服务器异常！','error');
			});*/	
		}
	});
}

function reset() {
	/*
	 * 关闭父页面的选项卡 var curTab = parent.$('#mainTabs').tabs('getSelected'); var
	 * currentTabIndex = parent.$('#mainTabs').tabs('getTabIndex',curTab);
	 * parent.$('#mainTabs').tabs('close',currentTabIndex);
	 */
	$('#permissiondg').treegrid('clearChecked');
	$('#permissiondg').treegrid('reload');	
}
