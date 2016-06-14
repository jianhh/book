// JavaScript Document
/**
 * @class zUtils
 * @type 工具集
 * @author WhiteMaNz
 * @description 基于 jQuery、artDialog、jQuery validate
 * @version 1.0
 */

// 扩展Date的format格式化日期时间
Date.prototype.format = function(mask) {

	var d = this;

	var zeroize = function(value, length) {

		if (!length)
			length = 2;

		value = String(value);

		for (var i = 0, zeros = ''; i < (length - value.length); i++) {

			zeros += '0';

		}

		return zeros + value;

	};

	return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|M{1,4}|yy(?:yy)?|([hHmstT])\1?|[lLZ])\b/g, function($0) {

		switch ($0) {

			case 'd' :
				return d.getDate();

			case 'dd' :
				return zeroize(d.getDate());

			case 'ddd' :
				return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];

			case 'dddd' :
				return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];

			case 'M' :
				return d.getMonth() + 1;

			case 'MM' :
				return zeroize(d.getMonth() + 1);

			case 'MMM' :
				return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];

			case 'MMMM' :
				return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];

			case 'yy' :
				return String(d.getFullYear()).substr(2);

			case 'yyyy' :
				return d.getFullYear();

			case 'h' :
				return d.getHours() % 12 || 12;

			case 'hh' :
				return zeroize(d.getHours() % 12 || 12);

			case 'H' :
				return d.getHours();

			case 'HH' :
				return zeroize(d.getHours());

			case 'm' :
				return d.getMinutes();

			case 'mm' :
				return zeroize(d.getMinutes());

			case 's' :
				return d.getSeconds();

			case 'ss' :
				return zeroize(d.getSeconds());

			case 'l' :
				return zeroize(d.getMilliseconds(), 3);

			case 'L' :
				var m = d.getMilliseconds();

				if (m > 99)
					m = Math.round(m / 10);

				return zeroize(m);

			case 'tt' :
				return d.getHours() < 12 ? 'am' : 'pm';

			case 'TT' :
				return d.getHours() < 12 ? 'AM' : 'PM';

			case 'Z' :
				return d.toUTCString().match(/[A-Z]+$/);

				// Return quoted strings with the surrounding quotes removed

			default :
				return $0.substr(1, $0.length - 2);

		}

	});

};

/**
 * JS拼接字符串
 */
function StringBuilder() {
	this.strings = new Array();

	this.append = function(str) {
		this.strings.push(str);
	};

	this.toString = function(separator) {
		return this.strings.join(separator || "");
	};
}

var DateUtils = {

	/**
	 * 根据日期获取当前是第几周
	 * 
	 * @param date
	 */
	getWeeks : function(d) {
		var startOfWeek = 1;
		var edate; // 当时

		if (zUtils.isUndefined(d)) {
			edate = new Date();
		} else {
			edate = new Date(d);
		}
		var sdate = new Date(edate.getFullYear(), 0, 1); // 当年的元旦

		// 相差毫秒数
		var time = edate.getTime() - sdate.getTime();

		// 减去前面不齐的日期
		time -= (7 - sdate.getDay()) * 1000 * 60 * 60 * 24;

		// 减去后面不齐的日期
		time -= edate.getDay() * 1000 * 60 * 60 * 24;

		var weeks = time / (1000 * 60 * 60 * 24 * 7);

		// 如果当年的元旦未到或刚好是指定的开始日期，则这个周算今年的第一周，应该补上
		if (sdate.getDay() <= startOfWeek) {
			weeks += 1;
		}

		// 如果当天已经超过或刚好是指定的开始日期，则这周补上
		if (edate.getDay() >= startOfWeek) {
			weeks += 1;
		}

		return Math.floor(weeks);
	},

	/**
	 * 根据年份、第几周、星期几，获得日期
	 * 
	 * @param year
	 * @param weeks
	 * @param weekDay
	 * @return
	 */
	getDateByWeek : function(year, weeks, weekDay) {
		if (zUtils.isUndefined(weekDay)) {
			weekDay = 0;
		}

		var date = new Date(year, 0, 1);
		var time = date.getTime();

		// 补全周数
		time += (weeks - 1) * 7 * 24 * 60 * 60 * 1000;

		// 补全多余出来的时间
		if (date.getDay() > 1) {
			time += (7 - date.getDay()) * 24 * 60 * 60 * 1000;
		}

		if (date.getDay() == 1) {
			time -= 1 * 24 * 60 * 60 * 1000;
		}

		// 补全到指定的星期几
		time += weekDay * 24 * 60 * 60 * 1000;

		date.setTime(time);

		return date;
	},

	/**
	 * 根据年份获取这一年有多少个星期
	 * 
	 * @param year
	 * @return
	 */
	getNumberOfWeekByYear : function(year) {
		var d = new Date(year, 0, 1);
		var num = 52;
		var startOfWeek = 1; // 星期一为新的一周开始

		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			if (d.getDay() == (startOfWeek - 1) || d.getDay() == startOfWeek)
				num += 1;
		} else {
			if (d.getDay() == startOfWeek)
				num += 1;
		}

		return num;
	}
};

/**
 * z 的工具集
 */
var zUtils = {

	/**
	 * 浏览器类型和版本
	 * 
	 * @type
	 */
	browser : {
		version : (navigator.userAgent.toLowerCase().match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
		webkit : /webkit/.test(navigator.userAgent.toLowerCase()),
		opera : /opera/.test(navigator.userAgent.toLowerCase()),
		msie : /msie/.test(navigator.userAgent.toLowerCase()) && !/opera/.test(navigator.userAgent.toLowerCase()),
		mozilla : /mozilla/.test(navigator.userAgent.toLowerCase()) && !/(compatible|webkit)/.test(navigator.userAgent.toLowerCase())
	},

	/**
	 * 打印信息
	 * 
	 * @param msg
	 */
	log : function(msg) {
		if (window.console) {
			window.console.log(msg);
		} else {
			window.alert(msg);
		}
	},

	/**
	 * ReturnMessage返回的提示
	 * 
	 * @param returnMessage
	 * @param callBack
	 */
	message : function(returnMessage, callBack) {

		if (returnMessage == null) {
			this.alert('返回消息为空！');
			return;
		}

		if (returnMessage.result) {
			this.success(returnMessage.message, callBack);
		} else {
			this.error(returnMessage.message, callBack);
		}
	},
//
//	/**
//	 * 警告提示
//	 * 
//	 * @param {}
//	 *            content
//	 * @param {}
//	 *            callback
//	 */
//	alert : function(content, callback) {
//		art.dialog.alert(content, callback);
//	},
//
//	/**
//	 * 消息提示
//	 * 
//	 * @param msg
//	 */
//	tip : function(msg, time) {
//		return artDialog({
//			id : 'Tips',
//			title : false,
//			cancel : false,
//			fixed : true,
//			lock : true
//		}).content('<div style="padding: 0 1em;">' + msg + '</div>').time(time || 3);
//	},
//
//	/**
//	 * 失败提示
//	 * 
//	 * @param msg
//	 */
//	error : function(msg, callBack) {
//		if (!zUtils.isFunction(callBack)) {
//			callBack = function() {
//				return true;
//			};
//		}
//
//		msg = msg ? msg : '操作失败！<br/>';
//
//		art.dialog({
//			icon : 'error',
//			title : '失败',
//			lock : true,
//			opacity : 0.3,
//			content : msg,
//			ok : true,
//			close : callBack
//		});
//	},
//
	/**
	 * 成功提示
	 * @param msg
	 * @param callBack
	 */
	success : function(msg, callBack) {
		if (!zUtils.isFunction(callBack)) {
			callBack = function() {
				return true;
			};
		}
		msg = msg ? msg : '操作成功！<br/>';
		
		layer.confirm('您是如何看待前端开发？', {
		    btn: ['重要','奇葩'] //按钮
		}, callBack, callBack );
		
		
		alert(msg);
		callBack;
//		art.dialog({
//			icon : 'succeed',
//			title : '成功',
//			lock : true,
//			opacity : 0.3,
//			content : msg,
//			ok : true,
//			close : callBack
//		});
	},
//
//	/**
//	 * 确认提示
//	 * 
//	 * @param msg
//	 * @param yes
//	 * @param no
//	 */
//	confirm : function(msg, yes, no) {
//		art.dialog.confirm(msg, yes, no);
//	},

	/**
	 * 基于jQuery validate的表单验证
	 * 
	 * @param obj
	 * @param func
	 */
	validate : function(obj, func) {
		if (!("validate" in obj)) {
			return;
		}

		$(obj).validate({
			errorClass : "validateError",
			ignore : ".ignoreValidate",
			errorPlacement : function(error, element) {

				var $msg = element.metadata().msg;
				if ("undefined" != typeof $msg && $msg != "") {
					showAlert($msg + error.text().replace("此内容", ""));
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
				if (typeof(func) == 'function') {
					func();
				} else {
					form.submit();
				}
			}
		});
	},

	/**
	 * 判断对象类型
	 * 
	 * @param o
	 * @return string
	 */
	type : function(o) {
		return jQuery.type(o);
	},

	/**
	 * 判断类型是否数组
	 * 
	 * @param o
	 * @return true | false
	 */
	isArray : function(o) {
		return jQuery.isArray(o);
	},

	/**
	 * 判断类型是否函数
	 * 
	 * @param o
	 * @return true | false
	 */
	isFunction : function(o) {
		return jQuery.isFunction(o);
	},

	/**
	 * 判断类型是否对象
	 * 
	 * @param o
	 * @return true | false
	 */
	isObject : function(o) {
		return jQuery.isPlainObject(o);
	},

	/**
	 * 判断类型是否字符串
	 * 
	 * @param o
	 * @return true | false
	 */
	isString : function(o) {
		return typeof(o) === "string";
	},

	/**
	 * 判断类型是否数字
	 * 
	 * @param o
	 * @return true | false
	 */
	isNumeric : function(o) {
		return jQuery.isNumeric(o);
	},

	/**
	 * 判断对象是否未定义
	 * 
	 * @param o
	 * @return true | false
	 */
	isUndefined : function(o) {
		return typeof(o) === "undefined";
	},

	/**
	 * 判断对象的值是否为空
	 * 
	 * @param o
	 * @return true | false
	 */
	isNull : function(o) {
		return null === o;
	},

	/**
	 * Json 转换成 String
	 * 
	 * @param json
	 * @return
	 */
	jsonToString : function(json) {
		return JSON.stringify(json);
	},

	/**
	 * String 转换成 Json
	 * 
	 * @param string
	 * @return JSON
	 */
	stringToJson : function(string) {
		return jQuery.parseJSON(string);
	},

	/**
	 * String 转换成 Number
	 * 
	 * @param string
	 * @return Number
	 */
	stringToNumber : function(string) {
		return window.parseInt(string);
	}

};
