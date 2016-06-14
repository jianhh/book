// JavaScript Document
/**
 * @class zAction
 * @author WhiteMaNz
 * @description 基于jQuery ajax，jQuery validate的表单验证和提交控件
 * @version 1.0
 */

(function($) {

    /**
     * @description 自动提交表单控件
     * @argument url (提交的地址｜可选｜默认为表单的action属性)
     * @argument method (提交的方法｜可选｜默认为表单的method属性，没有则为 POST)
     * @argument data (提交的数据｜可选｜默认为序列化表单的所有控件，如果填写则一定要以函数形式)
     * @argument before(前置函数｜可选｜提交表单前执行的函数)
     * @argument callback(回调函数｜可选｜参数为服务器返回的ReturnMessage)
     * @argument notMsgState 返回的state在配置的不显示消息的state里面，不显示提示信息，直接执行回调函数
     */
    $.fn.zAction = function(options) {
	var opts = $.extend({
	    url : null,
	    method : null,
	    data : null,
	    tipsType:null,
	    before : null,
	    callback : null,
	    ignoreValidate : ".ignoreValidate",
	    notMsgState : null
	}, options);

	var formObj = this;

	var lock = false;

	var isAllow = true;
	/**
	 * @description 设置是否允许提交表单
	 * @argument true（允许） | false（禁止）
	 */
	this.allowSubmit = function(is) {
	    isAllow = is;
	};

	var submitForm = function() {

	    // 执行前置函数
	    if (zUtils.isFunction(opts.before)) {
	    	opts.before();
	    }

	    // 禁止提交，返回
	    if (!isAllow) {
		return;
	    }

	    var url = opts.url || formObj.attr("action"), method = opts.method || formObj.attr("method") || "POST", data = zUtils.isFunction(opts.data) ? opts.data() : formObj.serialize();
     
	    $.ajax({
		url : url,
		type : method,
		async : true,
		timeout : 1000 * 60 * 10,
		dataType : "json",
		data : data,
		beforeSend : function(XHR) {
		    if (lock) {
		    	layer.msg("正在处理中，请勿重复操作！");
		    	return false;
		    }
		    lock = true;
		},
		success : function(res) {

		    if (zUtils.isFunction(opts.callback)) {
			// 返回的state在配置的不显示消息的state里面，不显示提示信息，直接执行回调函数
				if (!zUtils.isNull(opts.notMsgState) && opts.notMsgState.indexOf(res.state) > -1) {
				    opts.callback(res);
				} else {
					opts.callback(res);
				}
		    } else {
		    	layer.msg(res);
		    }
		},
		error : function(xhr, msg, exc) {
			layer.msg("提交出错，请稍后再试。",{icon:2});
		},
		complete : function(XHR, TS) {
		    lock = false;
		}
	    });
	};

	var showError = function(ele, msg, direction, bgcolor) {
	    switch (direction) {
	    case 'top':
		direction = 1;
		break;
	    case 'right':
		direction = 2;
		break;
	    case 'bottom':
		direction = 3;
		break;
	    case 'left':
		direction = 4;
		break;
	    default:
		direction = 1;
	    }

	    $(ele).tips({
		side : direction,
		msg : msg,
		bg : bgcolor || '#68B500',
		time : 3
	    });
	};

	// 表单提交，需要验证
	if (formObj.is("form")) {
	    formObj.validate({
		errorClass : "validateError",
		ignore : opts.ignoreValidate,
		onkeyup : false,
		onfocusin : false,
		onfocusout : false,
		focusInvalid : true,
		onclick : false,
		ignoreTitle : true,
		errorPlacement : function(error, element) {
		    var messagePosition = element.metadata().messagePosition;
		    var messageDirection = element.metadata().messageDirection;
		    var messageBgColor = element.metadata().messageBgColor;

		    if ("undefined" != typeof messagePosition && messagePosition != "") {
				var $messagePosition = $(messagePosition);
				if ($messagePosition.size() > 0) {
					if(zUtils.isNull(options.tipsType)){
						error.insertAfter($messagePosition).fadeOut(300).fadeIn(300);
					}else{
						showError($messagePosition, error.text(), messageDirection, messageBgColor);
					}
				} else {
					if(zUtils.isNull(options.tipsType)){
						error.insertAfter(element).fadeOut(300).fadeIn(300);
					}else{
						showError(element, error.text(), messageDirection, messageBgColor);
					}
				}
		    } else {
		    	if(zUtils.isNull(options.tipsType)){
		    		error.insertAfter(element).fadeOut(300).fadeIn(300);
		    	}else{
		    		showError(element, error.text(), messageDirection, messageBgColor);
		    	}
		    }
		},
		submitHandler : function(form) {
		    submitForm();
		}
	    });
	} else {
	    // 直接提交
	    submitForm();
	}
	return this;
    };

})(jQuery);
