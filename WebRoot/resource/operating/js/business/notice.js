top.hangge();

/**
 * 检索
 *
 */

function search(pageNum) {
    var size = $('#pageSize option:selected').val();

    $("#ajaxPager").zPager({
	target : $("#data_list"),
	template : $("#data_temp"),
	url : BASE.basePath + "/notice/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#searchForm').serialize(),
	id : "notice_{resNoticeId}",
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
    diag.URL = BASE.basePath + '/notice/toEditor' + (zUtils.isUndefined(id) ? "" : "?resNoticeId=" + id);
    diag.Width = 750;
    diag.Height = 500;
    diag.CancelEvent = function() { // 关闭事件

	diag.close();
    };
    diag.show();
}


// 删除
function del(resNoticeId, msg) {
    bootbox.confirm("确定要删除[" + msg + "]吗?", function(result) {
	if (result) {
	    $.ajax({
		type : "POST",
		url : BASE.basePath + '/notice/doDelete',
		data : {
		    'resNoticeId' : resNoticeId
		},
		success : function(rm) {
		    zUtils.message(rm, function() {
			if (rm.result) {
			    $('#notice_' + resNoticeId).fadeOut(500, function() {
				$(this).remove();

				$('#totalCount').text(parseInt($('#totalCount').text()) - 1);
			    });
			}
		    });
		}
	    });
	}
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
    	zUtils.alert("请选择要删除的通知公告！");
        return false;
    }
	
    bootbox.confirm("确定要删除[" + "选中的通知公告" + "]吗?", function(result) {
		if (result) {
		    $.ajax({
			type : "POST",
			url : BASE.basePath + '/notice/doDelete',
			data: {
				'resNoticeId':checkedList.toString()
				}, 
			success : function(rm) {				
			    zUtils.message(rm, function() {
				if (rm.result) {
					//循环选中的input，逐个移除列表数据
					strParameters.each(function() {
						
						var resNoticeId = $(this).val();
						
						 $('#notice_' + resNoticeId).fadeOut(500, function() {
							 
		    				$(this).remove();
		    				
		    				$('#totalCount').text(parseInt($('#totalCount').text()) - 1);
	    			    });
					}); 
					
				   
				    
				}
			    });
			}
		    });
		}
    }); 
}

// 下拉框
$(".chzn-select").chosen({
    disable_search_threshold : 10
});

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
	url : BASE.basePath + "/notice/doSave",
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
	
	if($('#resNoticeAnnex').val()==''){
		zUtils.error("附件必须上传!");
		return false;
	}
	
	
	$('textarea[name="resNoticeContent"]').val(CKEDITOR.instances.resNoticeContent.getData());
	
    $('#saveForm').submit();
}

// 取消
function cancel() {
    parent.Dialog.close();
}



/**
 * 新增或编辑的时候选择区/县 将区/县 ID 通过ajax 请求到后台返回可选学校的列表 obj 为 省 select 控件对象
 */
function choice_dic(obj, tyep) {
	var curr = $(obj).find('option:selected');
	$(':hidden[name="' + tyep + '"]').val(curr.text());
}

//渲染上传控件
if ('zUpload' in $('#resNoticeAnnexButton')) {	
	var zUpload = $('#resNoticeAnnexButton').zUpload({	
		url : BASE.basePath + '/notice/doUpload',
		fieldName : 'myFile',
		fileSize : 2 * 1024 * 1024,
		fileCount : 9,
		multiple : true,
		success : function(data) {			
				if (data.result == true) {	
					$('#resNoticeAnnexA').closest('.info').show();
					$('#resNoticeAnnex').val(data.map.filePath);	
					$('#resNoticeAnnexP').show();
					$('#resNoticeAnnexA').attr("href",BASE.basePath +data.map.filePath);			  
				} else {
					zUtils.error(data.message);
				}
		},
		error : function(e) {
			zUtils.error("上传失败");
		}
	});
	//alert("完成渲染了");
}


