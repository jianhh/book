// JavaScript Document
/**
 * @class zUpload
 * @type 上传控件
 * @author WhiteMaNz
 * @description 基于 jQuery、zUtils
 * @version 1.1
 */

// 给 JQUERY对象 添加zUpload 方法

(function($) {
	/**
	 * @description 上传控件
	 * @argument url (提交的地址｜必选)
	 * @argument fieldName (文件名｜可选｜默认为"file"，此值对应后台接收数据的键)
	 * @argument data (提交的数据｜可选｜随文件一起提交的数据)
	 * @argument fileSize (文件大小｜可选｜限制上传文件的大小)
	 * @argument fileCount (文件数量｜可选｜限制上传文件的数量)
	 * @argument allowedTypes (允许类型｜可选｜限制上传文件的类型)
	 * @argument success(成功后的回调函数｜可选｜参数为经过处理后的JSON数据)
	 * @argument error(错误后的回调函数｜可选｜参数为数据转换错误的信息)
	 */
	
	//jQuery.extend(object);为扩展jQuery类本身.为类添加新的方法。 
	//jQuery.fn.extend(object);给jQuery对象添加方法。
	$.fn.zUpload = function(options) {       //$.fn是指jquery的命名空间，加上fn上的方法及属性，会对jquery实例每一个有效。
		//alert("进入zUpload");
		var opts = $.extend({
			debug : false,
			url : "",
			fieldName : "file",
			data : {},
			fileSize : null,
			fileCount : null,
			allowedTypes : null,
			success : null,
			error : null,
			multiple : false
		}, options);    // var newSrc=$.extend({},src1,src2,src3...) 它的含义是将options 合并到{}里面去   ，也就是获取参数

		if (!('multiple' in $('<input type="file">').get(0))) {
			// 对于不支持多个上传的浏览器强制使用单个上传
			opts.multiple = false;

			if (opts.debug) {
				layer.msg(" 您的浏览器不支持多个上传，请使用支持HTML5的浏览器。 ");
			}
		}

	    lock = false, compatibilityMode = zUtils.browser.msie;  // 兼容模式

		var btnObj = this;

		var iframeName = "zUpload_iframe_" + new Date().getTime();
		
		
//		$("#resResourcesSourcePath_a").text(iframeName);

		
		// 文件数量控制
		btnObj.fileCount = {

			/**
			 * 文件数量总数
			 */
			totalFileCount : 0,

			/**
			 * 当前上传的文件数量
			 */
			currentFileCount : 0,

			/**
			 * 文件数量增加
			 * 
			 * @param num(文件数量增加量，不传入此参数则默认为本次上传的数量)
			 */
			incr : function(num) {
				num = zUtils.isUndefined(num) ? btnObj.fileCount.currentFileCount : num;
				btnObj.fileCount.totalFileCount = btnObj.fileCount.totalFileCount + num;
			},

			/**
			 * 文件数量减少
			 * 
			 * @param num(文件数量减少量，不传入此参数则默认为本次上传的数量)
			 */
			decr : function(num) {
				num = zUtils.isUndefined(num) ? btnObj.fileCount.currentFileCount : num;
				btnObj.fileCount.totalFileCount = btnObj.fileCount.totalFileCount - num < 0 ? 0 : btnObj.fileCount.totalFileCount - num;
			}
		};

		// 获取对象的top
		var getObjTop = function() {
			return btnObj.position().top + zUtils.stringToNumber(btnObj.css('margin-top').replace('px', ''));
		};

		// 获取对象的left
		var getObjLeft = function() {
			return btnObj.position().left + zUtils.stringToNumber(btnObj.css('margin-left').replace('px', ''));
		};

		// 兼容模式处理
		var compatibilityModeHandle = function() {
			$("#div"+iframeName).css({
				'width' : btnObj.outerWidth(),
				'height' : btnObj.outerHeight(),
				'position' : 'absolute',
				'opacity' : '0',
				'padding' : '0px',
				'overflow' : 'hidden',
				'z-index' : '188'
			});
			$("#size"+iframeName).css({
				'width' : btnObj.outerWidth(),
				'height' : btnObj.outerHeight(),
				'font-size' : btnObj.width() * btnObj.height() / 100,
				'margin' : '0px'
			});
			$(btnObj).mouseenter(function() {
				$("#div"+iframeName).css({
					'display' : 'block',
						'top' : getObjTop(),
						'left' : getObjLeft()
				});
			});
		};
		

		var html = new StringBuilder();
		html.append('<div id="div'+iframeName+'" style="display:none;">');
		html.append('<iframe name="' + iframeName + '" id="' + iframeName + '" src="" style="display:none;"></iframe>');
		html.append('<form method="post" id="form'+iframeName+'" enctype="multipart/form-data" target="' + iframeName + '" action="' + opts.url + '">');
		
		html.append('<input type="file" id="size'+iframeName+'"');

		// 多个上传
		if (opts.multiple) {
			html.append(' multiple="multiple" ');
		}

		html.append(' name="' + opts.fieldName + '" id="FILE' + iframeName + '" tabindex="-1" title="请选择文件" class="ignoreValidate" />');
//		html.append('<input type="hidden" nane="maxSize" value="'+ opts.fileSize +'" />'  );
		html.append('</form>');
		html.append('</div>');


		
		var div = $(html.toString());

		btnObj.after(div);

		if (compatibilityMode) {
			compatibilityModeHandle();
		} else {
			$(btnObj).click(function() {
				$("#size"+iframeName).click();
			});
		}
	
		
		//上传文件改变时触发
		$("#form"+iframeName).on("change","#size"+iframeName,function(e) {
			
			// 获取上传文件的大小
	        var file_size = 0;
	       
	       	file_size = $('#size' + iframeName)[0].files[0].size;//byte
	       	 
	        //将最大的文件大小转成M
	        var maxSize = opts.fileSize /1024/1024;
	        
            if(file_size > opts.fileSize){
            	//判断最大的文件大小是否超过G
            	if(maxSize > 1024){
            		maxSize = maxSize/1024;
            		zUtils.alert("上传的资源文件太大了  建议上传"+ maxSize +"G大小以内的文件！");
            	}
            	else{
            		zUtils.alert("上传的资源文件太大了  建议上传"+ maxSize +"M大小以内的非视频文件！");
            	}
            	
            	if($.browser.msie){
//            		$('#form' + iframeName).empty();
            		// 多个上传
            		if (opts.multiple) {
            			$('#form' + iframeName).html('<input multiple="multiple" type="file" id="size'+iframeName+'" name="' + opts.fieldName + '" tabindex="-1" title="请选择文件" class="ignoreValidate" />');
            		}
            		else{
            			$('#form' + iframeName).html('<input type="file" id="size'+iframeName+'" name="' + opts.fieldName + '" tabindex="-1" title="请选择文件" class="ignoreValidate" />');
            		}
            		
            		compatibilityModeHandle();
            	}
            	else{
            		//清空上传空间的值
                	$(this).attr("value","");
            	}
        		
            	return
            }
			
			if ($(this).val() == "") {
				
				return;
			}

			if (lock) {
				layer.msg("正在上传中，请勿重复操作！",{icon:0});
				return;
			}
			
			if($('#showView').length == 0){
				// 遮罩层(显示上传进度条)star
				$('body').prepend('<div id="showView"></div>');
				//遮罩层 
				$('#showView').append('<div class="show_shade"></div>');
				$('#showView').append('<div id="show_schedule"></div>'); //显示进度条的地方(在此下面处插入进度条代码)
				$('#show_schedule').append('<div id="show_top"><span class="show_name">正在上传文件</span><span id="show_closed">关闭</span></div>'); //显示框头部(关闭按钮)
				$('#show_schedule').append('<div id="showSlip"><div class="showSlip_main"><div id="progressBar"><div id="uploaded"></div></div></div><div id="info"></div></div>'); //在此处替换进度条
				//遮罩层(显示上传进度条)end
			}

			// 重置当前上传的文件数量
			btnObj.fileCount.currentFileCount = 0;

			// 多个上传，统计文件数量
			if (opts.multiple) {
				var file, files = $(':file', div).get(0).files;
				for (var f in files) {
					file = files[f];
					if (zUtils.type(file) == 'object') {
						btnObj.fileCount.currentFileCount++;

						if (opts.debug) {
							zUtils.log("name:" + file.name + "; size:" + file.size + "; type:" + file.type);
						}
					}
				}
			} else {
				btnObj.fileCount.currentFileCount = 1;
			}

			// 判断文件数量 -- Start
			if (zUtils.isNumeric(opts.fileCount)) {
				if (btnObj.fileCount.currentFileCount > opts.fileCount - btnObj.fileCount.totalFileCount) {
					layer.msg("文件数量超过了限制，您还能上传" + (opts.fileCount - btnObj.fileCount.totalFileCount) + "个文件");
					return;
				}
			} else if (zUtils.isObject(opts.fileCount)) {
				if (btnObj.fileCount.currentFileCount > opts.fileCount.max - btnObj.fileCount.totalFileCount) {
					if (zUtils.isFunction(opts.fileCount.error)) {
						opts.fileCount.error("max");
					} else {
						layer.msg("文件数量超过了限制，您还能上传" + (opts.fileCount.max - btnObj.fileCount.totalFileCount) + "个文件");
					}
					return;
				}
			}
			// 判断文件数量 -- End

			var hidden = new StringBuilder();
			for (var k in opts.data) {
				hidden.append('<input type="hidden" name="' + k + '" value="' + (zUtils.isFunction(opts.data[k]) ? opts.data[k]() : opts.data[k]) + '" />');
			}
			$('form', div).append(hidden.toString());

			var iframe = $('iframe[name="' + iframeName + '"]');
			iframe.bind('load', function() {

				lock = false;
				$('#showView').hide();
//				if($('#save_loading').length > 0){
//					$('#save_loading').hide();
//					$('#saveForm').show();
//				}

				iframe.unbind();
				iframe.attr('src', 'javascript:false');

				$('form', div)[0].reset();
				for (var k in opts.data) {
					$('form', div).find(':hidden[name="' + k + '"]').remove();
				}

				var str = iframe.contents().find('body').html(), data = null;

				// 将返回的HTML字符串转换为JSON对象
				try {
					data = zUtils.stringToJson(str);
				} catch (e) {
					data = null;

					if (zUtils.isFunction(opts.error)) {
						opts.error(e);
					} else {
						if (opts.debug) {
							zUtils.log(e);
						}
					}
				}
				
				if (data) {
					btnObj.fileCount.incr();

					if (zUtils.isFunction(opts.success)) {
						opts.success(data);
						
					}
				}

			});
			

			try {

				$('form', div)[0].submit();
				lock = true;
				
//				if($('#save_loading').length > 0){ 
//				    $('#saveForm').hide();
//				    $('#save_loading').show();
//				}
				
				
				$("#uploaded").css("width", 0);
				$("#progressBar").hide();
				 $("div#info").html("正在上传中..." );
				
				$('#showView').show();
				
				window.setTimeout("getProgressBar()", 500);
				   
			} catch (e) {
				layer.msg("上传失败，请重试",{icon:2});
				lock = false;

				iframe.unbind();
				iframe.attr('src', 'javascript:false');

				$('form', div)[0].reset();
				for (var k in opts.data) {
					$('form', div).find(':hidden[name="' + k + '"]').remove();
				}

				compatibilityModeHandle();
			}
		});
		
		//关闭上传遮罩框
		$("#show_closed").live("click",function(){
			$('#showView').hide();
			clearTimeout(interval);
			var iframe = $('iframe[name="' + iframeName + '"]');
			var ha = iframe.remove();
			lock = false;
			ha.prependTo('#FILE' + iframeName ); 
		});

		return this;

	};
	

	
})(jQuery);


var startTime;
function getProgressBar() {
	

	var timestamp = (new Date()).valueOf();
	var bytesReadToShow = 0;
	var contentLengthToShow = 0;
	var bytesReadGtMB = 0;
	var contentLengthGtMB = 0;
	$.getJSON("/fileStatus/upfile/progress", {"t":timestamp}, function (json) {
		var bytesRead = (json.bytesRead / 1024).toString();
		if (bytesRead > 1024) {
			bytesReadToShow = (bytesRead / 1024).toString();
			bytesReadGtMB = 1;
		}else{
			bytesReadToShow = bytesRead.toString();
		}
		var contentLength = (json.contentLength / 1024).toString();
		if (contentLength > 1024) {
			contentLengthToShow = (contentLength / 1024).toString();
			contentLengthGtMB = 1;
		}else{
			contentLengthToShow= contentLength.toString();
		}
		bytesReadToShow = bytesReadToShow.substring(0, bytesReadToShow.lastIndexOf(".") + 3);
		contentLengthToShow = contentLengthToShow.substring(0, contentLengthToShow.lastIndexOf(".") + 3);
		if (bytesRead == contentLength && json.contentLength!=0) {
			$("#close").show();
			$("#uploaded").css("width", "100%");
			if (contentLengthGtMB == 0) {
				$("div#info").html("\u4e0a\u4f20\u5b8c\u6210\uff01\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "KB.\u5b8c\u6210100%");
			} else {
				$("div#info").html("\u4e0a\u4f20\u5b8c\u6210\uff01\u603b\u5171\u5927\u5c0f" + contentLengthToShow + "MB.\u5b8c\u6210100%");
			}
			window.clearTimeout(interval);
			
		} else {
			var pastTimeBySec = (new Date().getTime() - startTime) / 1000;
			var sp = (bytesRead / pastTimeBySec).toString();
			var speed =  json.speed;
			var percent = json.percent;
			$("#uploaded").css("width", percent);
			if (bytesReadGtMB == 0 && contentLengthGtMB == 0) {
				$("div#info").html("\u4e0a\u4f20\u901f\u5ea6:" + speed +"kb/s" +"   \u002c  " + "\u5df2\u4e0a\u4f20:" + bytesReadToShow +"KB" +"   \u002c  " + "\u5171" + contentLengthToShow  + " KB, \u5b8c\u6210" + percent);
			} else {
				if (bytesReadGtMB == 0 && contentLengthGtMB == 1) {
					$("div#info").html("\u4e0a\u4f20\u901f\u5ea6:" + speed +"kb/s" +"   \u002c  " + "\u5df2\u4e0a\u4f20:" + bytesReadToShow +"KB" +"   \u002c  " + "\u5171" + contentLengthToShow  + " MB, \u5b8c\u6210" + percent);
				} else {
					if (bytesReadGtMB == 1 && contentLengthGtMB == 1) {
						$("div#info").html("\u4e0a\u4f20\u901f\u5ea6:" + speed +"kb/s" +"   \u002c  " + "\u5df2\u4e0a\u4f20:" + bytesReadToShow +"\u004d\u0042" +"   \u002c  " + "\u5171" + contentLengthToShow + " MB, \u5b8c\u6210" + percent);
					}
				}
			}
		}
		$("#progressBar").show();
	});
	
	 interval = window.setTimeout("getProgressBar()", 500);
	
}

