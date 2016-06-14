<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel='icon' href='<@front.img/>/title-logo.ico' type='image/x-ico' />
<link rel="stylesheet" href="<@front.css/>/saved_resource.css" type="text/css">
<link rel="stylesheet" media="screen" href="<@front.css/>/zzsc.css" />
<script src="<@front.js/>/jquery.min.js"></script>
<script src="<@base.js/>/layer/layer.js"></script>
<style>
html,body {
	height: 100%;
	background: url('<@front.img/>/gs.png');
	background: 
		linear-gradient(rgba(196, 102, 0, 0.2), rgba(155, 89, 182, 0.2)), 
		url('<@front.img/>/gs.png');
	font: 14px/1.5 "Microsoft Yahei", "Hiragino Sans GB", Helvetica, "Helvetica Neue", "微软雅黑", Tahoma, Arial, sans-serif;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Segoe UI", "Microsoft Yahei", "微软雅黑", Tahoma, Arial, STHeiti, sans-serif;
}
input{
font: 14px/1.5 "Microsoft Yahei", "Hiragino Sans GB", Helvetica, "Helvetica Neue", "微软雅黑", Tahoma, Arial, sans-serif;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Segoe UI", "Microsoft Yahei", "微软雅黑", Tahoma, Arial, STHeiti, sans-serif;
}
[class^="icon-"], [class*=" icon-"] {
font-family: 'icomoon' !important;
speak: none;
font-style: normal;
font-weight: normal;
font-variant: normal;
text-transform: none;
line-height: 1;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}
</style>
</head>
	<form id="msform">
	<input type="hidden" name="lastUrl" value="${lastUrl!}" />
	<!-- progressbar -->
	<ul id="progressbar">
		
	</ul>
	<!-- fieldsets -->
	<fieldset style="margin-top:40px;">
		<h2 class="fs-title">登录</h2>
		<h3 class="fs-subtitle"></h3>
		<input type="text" name="username" placeholder="请输入账号" />
		<input type="password" class="pw" name="password" placeholder="请输入密码" />
		
		<div class="rlf-group rlf-appendix clearfix">
			<label for="auto-signin" class="rlf-autoin l" hidefocus="true"><input type="checkbox" checked="checked" style="width:16px;" class="auto-cbx" id="auto-signin">下次自动登录</label>
			<a href="/user/newforgot" class="rlf-forget r" target="_blank" hidefocus="true">忘记密码 </a>
		</div>
		
		<input type="button" onclick="login(this)" class="next action-button" value="登录" />
		
		<div class="rl-model-footer">
			<div class="pop-login-sns clearfix">
				<span class="l">其他方式登录</span>
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=weibo" class="pop-sns-weibo r"><i class="icon-weibo"></i></a>
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=qq" class="pop-sns-qq r"><i class="icon-qq"></i></a>
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=weixin" class="pop-sns-weixin r"><i class="icon-weixin"></i></a>
			</div>
		</div>
	</fieldset>
</form>
<script>
	function login(obj){
		$(obj).val("正在登录中...");
		$.post("<@front.basePath/>/index/doLogin",$("#msform").serialize(),function(data){
			$(obj).val("登录");
			if(data.result){
				var url = $('input[name="lastUrl"]').val();
	    		var nowUrl = window.location.href;
	    		if(url == nowUrl || url == ""){
	    			window.location.href="/index";
	    		}else{
	    			window.location.href=url;
	    		}
			}else{
				layer.msg(data.message);
			}
		});
	}
	$('.pw').keyup(function(event) {
	    if (event.keyCode == 13) {
	    	$(".action-button").trigger("click");
	    }
	});
</script>
</body>
</html>