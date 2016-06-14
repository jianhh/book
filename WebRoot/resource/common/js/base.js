if (!window.XMLHttpRequest) {

    // 解决IE6透明PNG图片BUG
    DD_belatedPNG.fix(".png");

    // 解决IE6不缓存背景图片问题
    document.execCommand("BackgroundImageCache", false, true);

}

/**
 * jQuery ajax 全局设置
 */
jQuery.ajaxSetup({
    global : true,
    async : true,
    timeout : 1000 * 60 * 10,
    dataType : "json",
    type : "POST",
    error : function(data) {
	//zUtils.alert("请求超时");
    },
    dataFilter : function(data, type) {
	if (data.result == false && data.state == "reLogin") {
		alert(alert(data.message));
	    //zUtils.alert(data.message, function() {
		window.location.href = BASE.basePath + "/yun/login";
	    //});
	}
	return data;
    }
});

// 表单验证
function formValidate(obj, func) {
    if (!("validate" in obj)) {
	return;
    }

    $(obj).validate({
	errorClass : "validateError",
	ignore : ".ignoreValidate",
	errorPlacement : function(error, element) {

	    var $msg = element.metadata().msg;
	    if ("undefined" != typeof $msg && $msg != "") {
		 alert($msg + error.text().replace("此内容", ""));
	    } else {
		var messagePosition = element.metadata().messagePosition;
		if ("undefined" != typeof messagePosition && messagePosition != "") {
		    var $messagePosition = $(messagePosition);
		    if ($messagePosition.size() > 0) {
			error.insertAfter($messagePosition).fadeOut(300).fadeIn(300);
		    } else {
			error.insertAfter(element).fadeOut(300).fadeIn(300);
		    }
		} else {
		    error.insertAfter(element).fadeOut(300).fadeIn(300);
		}
	    }
	},
	submitHandler : function(form) {
	    if (typeof (func) == 'function') {
		func();
	    } else {
		form.submit();
	    }
	}
    });
};
