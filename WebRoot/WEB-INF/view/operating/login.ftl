<@operating.top "登录界面" />
<link rel="stylesheet" href="<@operating.css />/login/matrix-login.css" />
<link rel="stylesheet" href="<@operating.css />/login/camera.css" />
<link rel="stylesheet" href="<@operating.css />/login/dl_popup.css" />
<style>
body{
input: 14px/1.5 "Microsoft Yahei", "Hiragino Sans GB", Helvetica, "Helvetica Neue", Tahoma, Arial, sans-serif;
}
</style>

	<div style="width:100%;text-align: center;margin: 0 auto;position: absolute;background:url('<@front.img/>/gs.png')" class="background_Img">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" style="background:rgba(109, 98, 100, 0.62);" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<!--<img src="<@operating.img/>/login/logo.png" alt="Logo" /> -->
						二手书 后台管理登录
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_blue" style="padding:8.3px 9px;">
							<i><img height="37" src="<@operating.img/>/login/user.png" /></i>
							</span><input maxlength="16" type="text" autofocus="autofocus" name="userName" id="loginname" class="{required: true, messageDirection:'right'}" value="" placeholder="请输入账号或者邮箱" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_blue" style="padding:8.3px 9px;">
							<i><img height="37" src="<@operating.img/>/login/suo.png" /></i>
							</span><input maxlength="16" type="password" name="password" id="password" class="{required: true, messageDirection:'right'}"  placeholder="请输入密码" value="" />
						</div>
					</div>
				</div>
	
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;">
							<i><img src="<@operating.img />/login/yan.png" /></i>
						</div>
						<div style="float: left;margin-top: 2.5px;" class="codediv">
							<input type="text" maxlength="4" name="captcha" id="code" class="login_code {required: true, messageDirection:'bottom'}"
								style="height:16px; padding-top:0px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="codeImg" alt="点击更换"
								title="点击更换" src="<@base.basePath/>/Kaptcha.jpg" /></i>
						</div>

						<span class="pull-right" style="padding-right:3%;">
						<#--
							<a href="javascript:quxiao();" class="btn btn-success">取消</a></span><span class="pull-right">
						-->	
							<a onclick="severCheck();"class="flip-link btn btn-info" id="to-recover">登录</a>
						</span>
					</div>
				</div>
			</form>

			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr"> </span></font>
				</div>
			</div>
		</div>
	</div>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>

	<script src="<@operating.js />/jquery.easing.1.3.js"></script>
	<script src="<@operating.js />/jquery.mobile.customized.min.js"></script>
	<script src="<@operating.js />/camera.min.js"></script>
	
	<script src="<@operating.js />/login/login.js"></script>
	
<@operating.bottom />