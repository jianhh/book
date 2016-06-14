	<@front.top "注册-二手书"/>
	<link rel="stylesheet" href="<@front.css/>/register.css" />
	<link rel="stylesheet" href="<@front.css/>/layout.css"/>

	<div id="wrapper" style="background:#E9EAEB;padding-bottom: 30px;">
		
		<div class="my-box-shadow container w960 mt20">
			<div class="processor" >
				<ol>
					<li class="current size1of2">
						<div class="step_line"></div>
						<div class="step_inner"> 
							<i class="icon_step">1</i>
							<h4>填写基本信息</h4>
						</div>
					</li>
					
					<li class="size1of2 no_extra">
						<div class="step_line"></div>
						<div class="step_inner"> 
							<i class="icon_step">2</i>
							<h4>邮箱激活</h4>
						</div>
					</li>
					
					<li class="size1of2 last">
						<div class="step_line"></div>
						<div class="step_inner"> 
							<i class="icon_step">3</i>
							<h4>完善资料</h4>
						</div>
					</li>
				</ol>
			</div>
			<div class="content">
				<div id="step1" class="step hide">
					<form action="" method="post" id="step1_frm">
						<div class="frm_control_group">
							<label class="frm_label">账号</label>
							<div class="frm_controls">
								<input type="text" name="username" class="frm_input account" maxlength="12"/>
								<p class="frm_tips">作为登录帐号，且全站显示的昵称，请填写未被注册的账号</p>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">邮箱</label>
							<div class="frm_controls">
								<input type="text" name="email" class="frm_input email" maxlength="32"/>
								<p class="frm_tips">作为登录帐号，请填写未被注册、未被帐号绑定的邮箱</p>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">密码</label>
							<div class="frm_controls">
								<input type="password" name="password" class="frm_input passwd"/>
								<p class="frm_tips">字母、数字或者英文符号，最短6位，区分大小写</p>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">确认密码</label>
							<div class="frm_controls">
								<input type="password" name="confirmpassword" class="frm_input passwd2"/>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">验证码</label>
							<div class="frm_controls verifycode">
								<input type="text" name="captcha" class="frm_input verifyCode" maxlength="4"/>
								<img id="kaptchaImage" src="<@front.basePath/>/Kaptcha.jpg" alt="" />
								<a class="changeVerifyCode" href="javascript:;">换一张</a>
							</div>
						</div>
						<div class="toolBar">
							<a id="nextBtn" class="btn btn_primary" href="javascript:;">下一步</a>
						</div>
					</form>
				</div><!-- // step1 end -->
				<div id="step2" class="step hide">
					<div class="w330">
						<strong class="f16">感谢注册，确认邮件已发送至你的注册邮箱 : <br /><span id="emialspan"></span></strong>
						<p class="c7b">请进入邮箱查看邮件，并激活帐号。</p>
						<p>
							<a class="btn btn_primary mt20" href="javascript:;" onclick="loginemail()">登录邮箱</a>
							<a class="btn btn_primary mt20" href="javascript:;" onclick="repeatSendEmail()">重新发送</a>
						</p>
						<p class="c7b mt20">没有收到邮件？</p>
						<p>1. 请检查邮箱地址是否正确，你可以返回 <a href="#" class="c46">重新填写</a></p>
						<p>2. 检查你的邮件垃圾箱</p>
						<p>3. 若仍未收到确认，请尝试 <a href="#" class="c46">重新发送</a></p>
					</div>
				</div><!-- // step2 end -->
				<div id="step3" class="step hide">
					<form action="" method="post" id="step3_frm">
						<div class="frm_control_group">
							<label class="frm_label">短号</label>
							<div class="frm_controls">
								<input type="text" name="shortphonenumber" class="frm_input shortphone"/>
								<p class="frm_tips">请填写学校集群网短号</p>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">手机号</label>
							<div class="frm_controls">
								<input type="text" name="phonenumber" class="frm_input phone"/>
								<p class="frm_tips">请填写常用手机号</p>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">学校</label>
							<div class="frm_controls">
								<select name="schoolid" style="padding:0px 0px 0px 10px" onchange="selectCollege(this)" class="frm_input school">
									<option value="">学校</option>
									<#list sList as s>
										<option value="${s.dicid}">${s.dicname}</option>
									</#list>
								</select>
								<p class="frm_tips">请选择学校</p>
							</div>
						</div>
						<div class="frm_control_group">
							<label class="frm_label">学院</label>
							<div class="frm_controls">
								<select name="collegeid" style="padding:0px 0px 0px 10px" class="frm_input college" id="college">
									<option value="">学院</option>
								</select>
								<p class="frm_tips">请选择学院</p>
							</div>
						</div>
						<!--<div class="frm_control_group">
							<label class="frm_label">手机验证码</label>
							<div class="frm_controls">
								<input type="text" name="" class="frm_input phoneYzm"/>
								<input type="button" value="获取验证码" class="btn btn_default" />
							</div>
						</div>-->
						<div class="toolBar">
							<a id="finishedBtn" class="btn btn_primary" onclick="finishReg()()" href="javascript:;">完成</a>
						</div>
						<input type="hidden" name="userid" value="" />
					</form>
				</div><!-- // step3 end -->
			</div>
		</div><!-- // container end -->
	</div><!-- // wrapper end -->
	<script src="<@front.js/>/jquery.min.js"></script>
	<script src="<@base.js/>/layer/layer.js"></script>
	<script src="<@front.js/>/business/register.js"></script>
	<!-- 底部 -->
	<@front.bottom/>