// JavaScript Document
/**
 * @class zPager
 * @author WhiteMaNz
 * @description 基于jQuery ajax，jQuery pager的分页查询控件
 * @version 1.1
 */
(function($) {

    /**
     * @description 自动渲染分页控件
     * @argument debug (调试模式｜可选｜默认为 false)
     * @argument load (是否即时加载｜可选｜默认为 true)
     * @argument url (请求地址｜必需)
     * @argument method (请求的方法｜可选｜默认为 GET)
     * @argument parameter 查询条件｜可选)
     * @argument pageNumber (页码｜可选｜默认为 1)
     * @argument pageSize (每页记录条数｜可选｜默认为 10)
     * @argument pageCount (总页数｜可选｜一般非即时加载时才需要此参数，默认为 5)
     * @argument target (列表显示的位置｜必需)
     * @argument template (列表中每条记录的模版｜必需)
     * @argument id (每条记录的ID｜可选｜默认是当前时间戳)
     * @argument field (存放字段的属性｜可选｜指定哪个属性存放着读取对应记录的字段，默认为 name)
     * @argument basePath (网站的基础地址｜可选｜默认为空字符串)
     * @argument fadeIn (列表淡入效果持续时间｜可选｜默认为 500 毫秒)
     * @argument fadeOut (列表淡出效果持续时间｜可选｜默认为 500 毫秒)
     * @argument callback (回调函数｜可选｜参数为服务器返回的ReturnMessage)
     * @argument isAlertFalseMessage  (是否显示返回查询信息|可选|默认false)
     * @argument isReplaceHtml  (是否以html的方式显示|可选|默认false)
     */

	/**
	 * 如果想要在分页查询截取字符长度，只要在相应的地方增加class="subStrNun"和size="(想要截取的字数)"2个属性，
	 * 默认状态下，截取字符后面+“...”，若想要不+“...”的话只要加class="showTime"即可。
	 */
	
    $.fn.zPager = function(options) {
	var obj = this;

	var opts = $.extend({
	    debug : false,
	    load : true,
	    url : "",
	    method : "GET",
	    parameter : {},
	    pageNumber : 1,
	    pageSize : 10,
	    pageCount : 5,
	    target : null,
	    template : null,
	    id : null,
	    field : "name",
	    basePath : "",
	    fadeIn : 500,
	    fadeOut : 500,
	    callback : null,
	    isAlertFalseMessage : true,
	    isReplaceHtml : false
	}, options);

	var fadeIn = opts.fadeIn, fadeOut = opts.fadeOut, template = $(opts.template[0]);

	// 隐藏模版
	template.hide();

	// 获取对象里的值
	var getObjectValue = function(field, val) {
	 
	    if (field.indexOf(".") > -1) {
		var fields = field.split(".");
		for ( var f in fields) {
		    if (zUtils.isUndefined(val) || zUtils.isNull(val)) {
			break;
		    }
		    val = val[fields[f]];
		}
	    } else {
		val = val[field];
		
	    }

	    if (opts.debug) {
		if (zUtils.isUndefined(val))
		    zUtils.log(field + " is undefined");
		if (zUtils.isNull(val))
		    zUtils.log(field + " is null");
	    }
	    return val;
	};

	// 替换变量
	var replaceVar = function(link, data) {
	    if (!zUtils.isNull(link) && !zUtils.isUndefined(link)) {
		var linkVal, arg, args = link.match(/{[^}]+}/g);
		if (args != null) {
		    for ( var i = 0; i < args.length; i++) {
			arg = String(args[i]).replace("{", "").replace("}", "");
			linkVal = getObjectValue(arg, data);

			if (!zUtils.isUndefined(linkVal) && !zUtils.isNull(linkVal)) {
			    if (zUtils.isArray(linkVal)) {
				return linkVal;
			    } else {
				link = String(link).replace(args[i], linkVal);
			    }
			} else {
			    // return null;
			}
		    }
		}
	    }
	    return link;
	};

	// 插入值
	var insertValue = function(data, elem, val) {

	    // if (!zUtils.isNull(elem.attr("id")) &&
	    // !zUtils.isUndefined(elem.attr("id"))) {
	    // elem.attr("id", replaceVar(elem.attr("id"), data));
	    // }
	    //
	    // // 优先渲染链接
	    // if (elem.is("a")) {
	    // if (!zUtils.isNull(elem.attr("href")) &&
	    // !zUtils.isUndefined(elem.attr("href"))) {
	    // elem.attr("href", replaceVar(elem.attr("href"), data));
	    // }
	    // }
	    //
	    // // 优先渲染input里的onclick
	    // if (elem.is("input")) {
	    // if (!zUtils.isNull(elem.attr("onclick")) &&
	    // !zUtils.isUndefined(elem.attr("onclick"))) {
	    // elem.attr("onclick", replaceVar(elem.attr("onclick"), data));
	    // }
	    // }

	    // 如果字段为空或没有值，跳过
	    // if (zUtils.isUndefined(val) || zUtils.isNull(val)) {
	    // return;
	    // }

	    // 如果字段不为空，执行替换操作

	    if (!zUtils.isUndefined(val) && !zUtils.isNull(val)) {
		// 替换文本
		if (!zUtils.isUndefined(elem.attr("replaceText"))) {
		    var replaceTexts = eval("(" + elem.attr("replaceText") + ")");
		    for ( var rt in replaceTexts) {
		    	val = val==rt?replaceTexts[rt]:val;
//			val = String(val).replace(rt, replaceTexts[rt]);
		    }
		    elem.removeAttr("replaceText");
		}

		// 替换HTML
		var isReplaceHtml = opts.isReplaceHtml;
		if (!zUtils.isUndefined(elem.attr("replaceHtml"))) {
		    var replaceHtmls = eval("(" + elem.attr("replaceHtml") + ")");
		    for ( var rh in replaceHtmls) {
			val = String(val).replace(rh, replaceHtmls[rh]);
			alert(val);
		    }
		    elem.removeAttr("replaceHtml");
		    isReplaceHtml = true;
		}

		// 格式化时间
		if (!zUtils.isUndefined(elem.attr("dateFormat"))) {
		    val = String(val).replace(/-/g, "/");
		    if(val.length<10){
		    	val+="/01";
		    }
		    val = new Date(val).format(elem.attr("dateFormat"));
		    elem.removeAttr("dateFormat");
		}

		if (elem.is("input")) {
		    // 输入框
		    elem.val(val);
		} else if (elem.is("img")) {
		    // 图像
		    elem.attr("src", opts.basePath + val + "?" + new Date().getTime());
		} else {
		    if (isReplaceHtml) {
			elem.html(val);
		    } else {
			elem.text(val);
		    }
		}
	    }
	    // 替换标签中的变量
	    if (elem.prop("outerHTML") != replaceVar(elem.prop("outerHTML"), data) && !zUtils.isNull(replaceVar(elem.prop("outerHTML"), data))) {
		elem.replaceWith(replaceVar(elem.prop("outerHTML"), data));
	    }
	};

	// 单个元素处理
	var handle = function(data, elem, val) {

	    // 单个数据字段
	    val = val || data;
	    var field = elem.attr(opts.field);

	    val = getObjectValue(field, val);

	    // 数组
	    if (zUtils.isArray(val)) {
		// 如果有子标签，则是对象列表
		if (elem.find("[" + opts.field + "]").length > 0) {
		    var child = elem.children();

		    // 如果只有一层
		    if (child.is("[" + opts.field + "]")) {
			$.each(val, function(i, v) {
			    handle(data, child.clone().insertBefore(child), v);
			});
			child.remove();
		    } else {
			$.each(val, function(i, v) {
			    $.each(child.clone().insertBefore(child).find("[" + opts.field + "]"), function(j, cv) {
				handle(data, $(cv), v);
			    });
			});
			child.remove();
		    }
		} else {
		    var child = elem.children();
		    $.each(val, function(i, v) {
			insertValue(data, child.clone().insertBefore(child), v);
		    });
		    child.remove();
		}

	    } else {
		insertValue(data, elem, val);
	    }
	};

	// 异步分页
	this.ajaxPager = function(pageNo) {

	    if (obj.attr('zPagerLocked') == "true") {
	    	
	    	if(opts.isAlertFalseMessage == true){
	    		return false;
	    	}
	    }
	    obj.attr('zPagerLocked', "true");

	    var parameter = opts.parameter;
      
	    if (zUtils.isFunction(parameter)) {
		parameter = parameter();
	    }

	    if (zUtils.isObject(parameter)) { // 参数是对象
		parameter = $.extend(parameter, {
		    'pageNumber' : pageNo,
		    'pageSize' : opts.pageSize
		});
	    } else if (zUtils.isArray(parameter)) { // 参数是数组
		parameter.push({
		    'name' : 'pageNumber',
		    'value' : pageNo
		});
		parameter.push({
		    'name' : 'pageSize',
		    'value' : opts.pageSize
		});
	    } else { // 参数是字符串
		parameter += "&pageNumber=" + pageNo + "&pageSize=" + opts.pageSize;
	    }

	    // 获取数据
	    $.ajax({
		url : opts.url,
		type : opts.method,
		async : true,
		timeout : 1000 * 60 * 10,
		dataType : "json",
		data : parameter,
		beforeSend : function(XHR) {

		},
		success : function(res) {
			
		    opts.target.fadeOut(fadeOut, function() {
			// 第一次是0秒，然后立即重新赋值成用户自定义的
			fadeOut = opts.fadeOut;

			// 清空列表
			opts.target.empty().show();

			if (res.result == true) {
			    var temp, pager = res.pager, list = pager.list, data;

			    if (!zUtils.isNull(list) && list.length > 0) {

				// 遍历返回的数据列表
				$.each(list, function() {
				    // 单个数据对象
				    data = this;

				    // 克隆模版
				    temp = template.clone();

				    if (zUtils.isNull(opts.id)) {
					temp.attr("id", new Date().getTime());
				    } else {
					temp.attr("id", replaceVar(opts.id, data));
				    }

				    // 渲染模版
				    temp.find("[" + opts.field + "]").each(function() {

					// 显示条件
					if (!zUtils.isUndefined($(this).attr("showIf"))) {
					    // 替换条件中的变量
					    var showIf = replaceVar($(this).attr("showIf"), data);

					    // if (!zUtils.isNull(showIf) &&
					    // !zUtils.isUndefined(showIf)) {
					    // 执行判断
					    //alert(showIf);
					   // alert(eval(showIf));
					    if (!eval(showIf)) {
							$(this).text("");
							//$(this).remove();
							return true;
					    }
					    // }
					    $(this).removeAttr("showIf");
					}
					
					// 去除条件
					if (!zUtils.isUndefined($(this).attr("removeIf"))) {
					    // 替换条件中的变量
					    var removeIf = replaceVar($(this).attr("removeIf"), data);
 
					    if (eval(removeIf)) {
							$(this).remove();
							return true;
					    }
					    // }
					    $(this).removeAttr("removeIf");
					}

					// 插入单个HTML元素对象
					handle(data, $(this));
				    });

				    temp.appendTo(opts.target).fadeIn(fadeIn);
				});
				// 第一次是0秒，然后立即重新赋值成用户自定义的
				fadeIn = opts.fadeIn;

			    }
			    // 重新渲染分页插件
			    $(obj).show().pager({
					pagenumber : pageNo,
					pagecount : pager.pageCount,
					buttonClickCallback : obj.ajaxPager
			    });
			    //显示  显示条数的li
			    $("#pager_num").show();

			} else {
			    if (!zUtils.isUndefined(res.message) && !zUtils.isNull(res.message)) {
			    	
			    	//isAlertFalseMessage==true 时不返回没有查询结果的信息提示。
			    	/*if(opts.isAlertFalseMessage == true){
			    		 layer.msg(res.message,{icon: 0});
			    	}*/
				     
			    }
			    // 隐藏分页插件
			    $(obj).hide();
			    
			    //隐藏  显示条数的li
			    $("#pager_num").hide();
			}

			// 执行回调函数
			if (zUtils.isFunction(opts.callback)) {
			    opts.callback(res);
			    
				//截取字符长度（如果需要截取字符长度则需要在相应加class="subStrNun"和size="（截取的字符数）"两个属性）
				var names = $('#data_list').find(".subStrNun");
				//循环列表
				names.each(function(){
					if($(this).hasClass("subStrNun")){
						var val = $(this).text();
						var nun = $(this).attr('size');
						var valLength = val.length;
						//判断如果文本长度大于要截取的长度则进行截取
						if(valLength > nun){
							var text = val.substr(0,nun);
							//如果是显示时间的内容，则直接截取，不加“...”
							if($(this).hasClass("showTime")){
								$(this).text(text);
							}
							else{
								$(this).text(text+"...");
							}
						}
					}
				});
				
				//设置用户的默认封面
				$("img.user").error(function(e){
					this.src="/resource/member/images/defUser.png";
				});

				//设置正方形的默认图片
				$("img.square").error(function(e){
					this.src="/resource/member/images/no_img300.jpg";
				});

				//设置长方形的默认图片
				$("img.long").error(function(e){
					this.src="/resource/member/images/no_img169.jpg";
				});

			}
		    });
		},
		error : function(xhr, msg, exc) {
			alert(parameter);
			alert(msg);
			alert(exc);
		    layer.msg("查询出错，请稍后再试。",{icon:2});
		},
		complete : function(XHR, TS) {
		    obj.attr('zPagerLocked', "false");
		}
	    });
	};

	// 立即加载
	if (opts.load == true) {
	    fadeIn = 0;
	    fadeOut = 0;
	    obj.ajaxPager(opts.pageNumber);
	} else {
	    // 非立即加载
	    $(obj).show().pager({
		pagenumber : opts.pageNumber,
		pagecount : opts.pageCount,
		buttonClickCallback : obj.ajaxPager
	    });
	}

	return this;
    };

})(jQuery);
