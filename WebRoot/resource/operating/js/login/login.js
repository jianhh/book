function severCheck() {   
    $('#loginForm').submit();
}

$('#loginForm').zAction({
    url : BASE.basePath + '/operatingBack/doLogin',
    method : 'POST',
    before : function() {
	$('#to-recover').text('正在登录中...');
    },
    notMsgState : [ "manyRole" ],   //表示返回的状态如果是 manyRole就不出现提示信息什么的
    callback : function(rm) {
	$('#to-recover').text('登录');

	if (rm.result) {
		window.location.href = BASE.basePath + "/operatingBack";  // 成功后直接跳转
	} else {
		layer.msg(rm.message,{icon:2});
	    changeCode();  // 不成功就修改 验证码
	}
    }
});


function genTimestamp() {
    var time = new Date();
    return time.getTime();
}

function changeCode() {
    $("#codeImg").attr("src", BASE.basePath + "/Kaptcha.jpg?t=" + genTimestamp());
}

changeCode();
$("#codeImg").bind("click", changeCode);

$(':input').keyup(function(event) {
    if (event.keyCode == 13) {
	$("#to-recover").trigger("click");
    }
});