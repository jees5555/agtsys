/**
 * 登录的js
 */

$(function() {
	$("#login-div").dialog({
		title : '系统登录',
		iconCls : "icon-ok",
		width : 420,
		height : 280,
		closable: false,
		draggable : false,
		cache : false,
		buttons:[{
			text:'登录',
			handler:function(){
				if($("#login-form").form('validate')){
			        $("#login-form").submit();
				}
			}
		},{
			text:'重置',
			handler:function(){
				$("#login-form").reset();
			}
		}]
	})
	//刷新验证码
	  $("#captchaButton").click(function(){
	      $("#captchaImg").attr("src","captcha/get?random="+Math.random());
	      $("#errors").html("");
	})
	//ajax判断验证码是否正确
	$("#captcha").keyup(function(){
	var captcha = $("#captcha").val();
	if(captcha.length==4){
	//启动ajax
		$.ajax({
			type: "POST",
			url : "captcha/check",
			data : "captcha="+captcha,
			dataType : "text",
			success : function(msg){
				if(msg=="yes"){
					$("#errors").html("<p style='color: green'>验证码正确</p>");
				}else{
					$("#errors").html("<p style='color: red'>验证码错误</p>");
				}
			}
		})
	}
})

})



function changeCaptcha(){
	document.getElementById("captchaImg").src="captcha/get?random="+Math.random();
}


