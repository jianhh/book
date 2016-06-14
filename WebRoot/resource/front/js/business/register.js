$(function(){
	//AJAX提交以及验证表单
	$('#nextBtn').click(function(){
		
		var email = $('.email').val();
		var passwd = $('.passwd').val();
		var passwd2 = $('.passwd2').val();
		var verifyCode = $('.verifyCode').val();
		var EmailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
		if(email == ''){
			layer.msg('请填写您的邮箱~');
		}else if(!EmailReg.test(email)){
			layer.msg('您的邮箱格式错咯~');
		}else if(passwd == ''){
			layer.msg('请填写您的密码~');
		}else if(passwd2 == ''){
			layer.msg('请再次输入您的密码~');
		}else if(passwd != passwd2 || passwd2 != passwd){
			layer.msg('两次密码输入不一致呢~');
		}else if(verifyCode == ''){
			layer.msg('请输入验证码~');
		}else{
			
			$.post("/index/doReg",$("#step1_frm").serialize(),function(data){
				
				if(!data.result){
					layer.msg(data.message);
					return;
				}
				
				layer.msg('提交成功~ 即将进入下一步');
				$("#emialspan").text(email);
				$("#step1").hide();
				$("#step2").fadeIn();
				$(".processor").find("li").eq(0).addClass("prev");
				$(".processor").find("li").eq(1).addClass("current");
				repeatSendEmail();
				//id
				$("input[name='userid']").val(data.o.userid);
				//发送请求是否成功激活邮箱
				var b = setInterval(function(){
					
					$.post("/index/isActive",{"id":data.o.userid},function(rm){
						
						if(rm.result){
							layer.msg("激活成功，请完善资料");
							$("#step2").hide();
							$("#step3").fadeIn();
							$(".processor").find("li").eq(1).addClass("prev");
							$(".processor").find("li").eq(2).addClass("current");
							clearInterval(b);
						}
					});
					
					
				},2000);
				
			});
		}
	});
	//生成验证码  
	$('#kaptchaImage').click(function () {
      $(this).hide().attr('src','/Kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); 
    });
	
	$('.changeVerifyCode').click(function () {
		$('#kaptchaImage').hide().attr('src','/Kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); 
	});
	
});
function loginemail(){
	var email = $("#emialspan").text();
	email.substring(email.lastIndexOf("@")+1);
	window.open("http://mail."+email);
}

function repeatSendEmail(){
	var email = $("#emialspan").text();
	$.post("/index/sendEmail",{"email":email},function(data){
		if(data.result){
			layer.msg("已经发送成功，请查收");
		}else{
			layer.msg(data.message);
		}
	});
	
}

function selectCollege(obj){
	var schoolid = $(obj).val();
	$.post('/userBack/doListByFatherId',{fatherId:schoolid},function(rm){
		if(rm.result){
			$("#college").empty();
			var html = '<option value="">学院</option>';
			$.each(rm.list,function(i,o){
				html = html + '<option value="'+this.dicid+'">'+this.dicname+'</option>';
			})
			$("#college").append(html);
		}
	});
}

function finishReg(){
	var shortphone = $('.shortphone').val();
	var phone = $('.phone').val();
	var school = $('.school').val();
	var college = $('.college').val();
	if(shortphone == ''){
		layer.msg('请填写您的短号~');
		return;
	}
	if(phone == ''){
		layer.msg('请填写您的长号~');
		return;
	}
	if(school == ''){
		layer.msg('请选择您的学校~');
		return;
	}
	if(college == ''){
		layer.msg('请选择您的学院~');
		return;
	}
	
	$.post("/index/completeReg",$("#step3_frm").serialize(),function(data){
		
		if(data.result){
			$(".processor").find("li").eq(2).addClass("prev");
			layer.msg("恭喜您，注册成功！");
			direction();
		}else{
			layer.msg(data.message);
		}
		
	});
	
}

var count=3;
function direction()
{ //计时
   if(count==0)
   {
     location.href = "/index/toLogin";
   }
   count-=1;
   setTimeout("direction()",1000);
}
