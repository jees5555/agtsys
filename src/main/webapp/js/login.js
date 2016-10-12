/**
 * 登录的js
 */

$(function() {
	$("#login-div").dialog({
		title : '系统登录',
		iconCls : "icon-ok",
		width : 420,
		height : 240,
		closed : false,
		cache : false,
		modal : true,
		buttons:[{
			text:'登录',
			handler:function(){
			 alert("login")
			}
		},{
			text:'重置',
			handler:function(){
				alert("reset")
			}
		}]
	})
})



function changeCaptcha(){
	document.getElementById("captchaImg").src="captcha/get?random="+Math.random();
}

$(document).ready(function(){
    $("#captchaButton").click(function(){
	    $("#captchaImg").attr("src","captcha/get?random="+Math.random());
	})
});

