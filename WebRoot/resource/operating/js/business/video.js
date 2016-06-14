top.hangge();

//下拉框
$(".chzn-select").chosen({
    disable_search_threshold : 10
});

/**
 * 检索
 *
 */

function search(pageNum) {
    var size = $('#pageSize option:selected').val();
    
    $(".innerWord").val($("#inputInnerWord").val());
    
    $("#ajaxPager").zPager({
	target : $("#data_list"),
	template : $("#data_temp"),
	url : BASE.basePath + "/video/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#searchForm').serialize(),
	id : "video_{videoId}",  
	field : "name",
	isReplaceHtml:true,
	pageNumber : pageNum || 1,
	pageSize : size || 10,
	callback : function(rm) {
	    if (rm.result) {
	    	$('#totalCount').text(rm.pager.totalCount);
	    }
	}
    });
}



/**
 * 修改界面
 *
 */
function editor(id) {
    var diag = new Dialog();
    diag.Drag = true;
    diag.Title = zUtils.isUndefined(id) ? "新增" : "修改";
    diag.URL = BASE.basePath + '/video/toEditor' + (zUtils.isUndefined(id) ? "" : "?videoId=" + id);
    diag.Width = 750;
    diag.Height = 600;
    diag.CancelEvent = function() { // 关闭事件

	diag.close();
    };
    diag.show();
}


// 删除
function del(articleId, msg) {
	
	
	layer.confirm("确定要删除[" + msg + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"删除文章"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/video/doDelete',
			data : {
				'videoId' : videoId
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					$("#video"+videoId).remove();
					$('#totalCount').text(parseInt($('#totalCount').text()) - 1);
				}else{
					layer.msg(rm.message, {icon: 2});
				}
			}
		});
	});
	
}

//批量删除
function delall(fieldName) {
	
	//创建一个数组用于组装选中的ID
	var checkedList = new Array(); 
	// 获取已经选的checkbox的值，并组装成 & 模式的URL 参数
	var strParameters = $("input[name='"+fieldName+"']:checked");
	//循环选中的input
	strParameters.each(function() {
		//将选中的value加入到checkedList
		checkedList.push($(this).val());
	}); 
	
	
	if(checkedList==null||checkedList==""){
		
		layer.msg("请选择要删除的新闻！");
		
        return false;
    }
	
	layer.confirm("确定要删除[" + "选中的新闻" + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"批量删除文章"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/video/doDelete',
			data : {
				'videoId' : checkedList.toString()
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					
					//循环选中的input，逐个移除列表数据
					strParameters.each(function() {
						
						var videoId = $(this).val();
						
						$('#video_' + videoId).fadeOut(200, function() {
							 
		    				$(this).remove();
		    				
		    				$('#totalCount').text(parseInt($('#totalCount').text()) - 1);
	    			    });
					}); 
					
				}else{
					layer.msg(rm.message, {icon: 2});
				}
			}
		});
	});
	
}


/** 新增/修改页面 * */

// 渲染提交表单
function initEditor() {
	
	 $(':input[untilDate]').datepicker({
	    	format : 'yyyy-mm-dd',
	    	weekStart : 1,
	    	autoclose : true,
	    	todayBtn : true,
	    	language : 'zh-CN'
    });
	    
    $('#saveForm').zAction({
	url : BASE.basePath + "/video/doSave",
	method : 'POST',
	before : function() {
	    $('#saveForm').hide();
	    $('#save_loading').show();
	},
	callback : function(rm) {

	    if (rm.result) {
		cancel();

		parent.search(1);
	    } else {
		$('#save_loading').hide();
		$('#saveForm').show();
	    }

	}
    });
    
}

// 保存
function save() {
	
	if($('#videoPicPath1').val()==''){
		layer.msg("视频缩略图必须上传!",{icon:2});
		return false;
	}
	var path = $('#videoContentPath').val();
	if(path==''){
		layer.msg("视频必须上传!",{icon:2});
		return false;
	}
	var exp = path.substring(path.lastIndexOf("."),path.length);
	alert(exp);
	
	
//	$('textarea[name="articleContent"]').val(CKEDITOR.instances.articleContent.getData());
//    $('#saveForm').submit();
}

// 取消
function cancel() {
    parent.Dialog.close();
}


/**
 * 渲染上传控件
 */
if ('zUpload' in $('#videoContentPath')) {
	var zUpload = $('#videoContentPath').zUpload({
		url : BASE.basePath + '/video/doVideoUpload',
		fieldName : 'myFile',
		fileSize :  3000 * 1024 * 1024,
		fileCount : 9,
		multiple : true,
		success : function(data) { 
			alert(1);
				if (data.result == true) {
					$('#videoContentPath_a').closest('.info').show();
					$('#videoContentPath_a').attr("href",data.map.filePath);
					$('#videoContentPath_b').val(data.map.filePath);
					$('#videoSize').val(data.map.fileSize);
					$('#fileTypeId').val(data.map.fileTypeId);
					$('#fileTypeName').val(data.map.fileTypeName);
				} else {
					zUtils.error(data.message);
				}
		},
		error : function(e) {
			zUtils.error("上传失败");
		}
	});
}





//渲染上传控件
if ('zUpload' in $('#videoPicPath')) {
	var zUpload = $('#videoPicPath').zUpload({
		url : BASE.basePath + '/video/doUpload',
		fieldName : 'myFile',
		fileSize : 20 * 1024 * 1024,
		allowedTypes : "jpg,png,jpeg",
		fileCount : 9,
		multiple : true,
		success : function(data) {
			alert(2);
				if (data.result == true) {
					$('#videoPicPath_a').closest('.info').show();
					$('#videoPicPath_a').attr('src',BASE.basePath + data.map.filePath);
					$('#videoPicPath_b').val(data.map.filePath);
				} else {
					zUtils.error(data.message);
				}
		},
		error : function(e) {
			zUtils.error("上传失败");
		}
	});
}


/*
//渲染上传控件
if ('zUpload' in $('#videoPicPath')) {	    
	var zUpload = $('#videoPicPath').zUpload({
		url : BASE.basePath + '/video/doUpload',
		fieldName : 'myFile',
		allowedTypes : "jpg,png,jpeg",
		fileSize : 2 * 1024 * 1024,
		fileCount : 9,
		multiple : true,
		success : function(data) {		
				alert();
			if (data.result == true) {
				alert(data.map.filePath);
				$('#videoPicPath1').attr("src",data.map.filePath);
				$('#videoPicPath1').show();				
				$("input[name='videoPicPath']").val(data.map.filePath);			
				
			} else {
				layer.msg(data.message,{icon:2});
			}
		},
		error : function(e) {
			alert(11);
			layer.msg("上传失败!",{icon:2});
		}
	});
}

//渲染上传控件
if ('zUpload' in $('#videoContentPath')) {	    
	    var zUpload1 = $('#videoContentPath').zUpload({
		url : BASE.basePath + '/video/doVideoUpload',
		fieldName : 'myFile',
		fileSize : 2 * 1024 * 1024,
		fileCount : 9,
		multiple : true,		
		success : function(data) {			
				alert(111);
				if (data.result == true) {	
					$("input[name='videoContentPath']").val(data.map.filePath);				
				} else {
					layer.msg(data.message,{icon:2});
				}
		},
		error : function(e) {
			layer.msg("上传失败!",{icon:2});
		}
	});
}*/

function changeHeight(obj){
	
	var h = $(obj).attr("oh");
	if(h==""){
		$(obj).attr("oh",$(obj).find("div").css("height"));
		$(obj).find("div").css("height","");
	}else{
		$(obj).find("div").css("height",$(obj).attr("oh"));
		$(obj).attr("oh","");
	}
	
}
