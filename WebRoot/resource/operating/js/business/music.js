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
	url : BASE.basePath + "/music/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#searchForm').serialize(),
	id : "buddhaMusic_{buddhaMusicId}",
	field : "name",
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
    diag.URL = BASE.basePath + '/music/toEditor' + (zUtils.isUndefined(id) ? "" : "?buddhaMusicId=" + id);
    diag.Width = 750;
    diag.Height = 500;
    diag.CancelEvent = function() { // 关闭事件

	diag.close();
    };
    diag.show();
}

/**
 * 查看界面
 *
 */
function view(id) {
    var diag = new Dialog();
    diag.Drag = true;
    diag.Title =  "查看";
    diag.URL = BASE.basePath + '/music/toView' + (zUtils.isUndefined(id) ? "" : "?buddhaMusicId=" + id);
    diag.Width = 900;
    diag.Height = 800;
    diag.CancelEvent = function() { // 关闭事件

	diag.close();
    };
    diag.show();
}


/**
 * 删除
 */ 
function del(buddhaMusicId, msg) {
    bootbox.confirm("确定要删除[" + msg + "]吗?", function(result) {
	if (result) {
	    $.ajax({
		type : "POST",
		url : BASE.basePath + '/music/doDelete',
		data : {
		    'buddhaMusicId' : buddhaMusicId
		},
		success : function(rm) {
		    zUtils.message(rm, function() {
			if (rm.result) {
			    $('#buddhaMusic_' + buddhaMusicId).fadeOut(500, function() {
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
    	zUtils.alert("请选择要删除的资源！");
        return false;
    }
	
    bootbox.confirm("确定要删除[" + "选中的资源" + "]吗?", function(result) {
		if (result) {
		    $.ajax({
			type : "POST",
			url : BASE.basePath + '/music/doDelete',
			data: {
				'buddhaMusicId':checkedList.toString()
				}, 
			success : function(rm) {				
			    zUtils.message(rm, function() {
				if (rm.result) {
					//循环选中的input，逐个移除列表数据
					strParameters.each(function() {
						
						var buddhaMusicId = $(this).val();
						
						 $('#buddhaMusic_' + buddhaMusicId).fadeOut(500, function() {
							 
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

/** 新增/修改页面 * */

/**
 *  渲染提交表单
 */
function initEditor() {
	
	 $(':input[untilDate]').datepicker({
	    	format : 'yyyy-mm-dd',
	    	weekStart : 1,
	    	autoclose : true,
	    	todayBtn : true,
	    	language : 'zh-CN'
    });
	    
    $('#saveForm').zAction({
	url : BASE.basePath + "/music/doSave",
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

/**
 * 保存
 */ 
function save() {
	if($('#buddhaMusicPath_b').val()==''){
		zUtils.error("资源文件必须上传!");
		return false;
	}
	
	
	
    $('#saveForm').submit();
}

/**
 * 取消
 */ 
function cancel() {
    parent.Dialog.close();
}

/**
 * 渲染上传控件
 */
if ('zUpload' in $('#buddhaMusicPath')) {
	var zUpload = $('#buddhaMusicPath').zUpload({
		url : BASE.basePath + '/music/doUpload',
		fieldName : 'myFile',
		fileSize :  3000 * 1024 * 1024,
		fileCount : 9,
		multiple : true,
		success : function(data) {
				if (data.result == true) {
					$('#buddhaMusicPath_a').closest('.info').show();
					$('#buddhaMusicPath_a').attr("href",data.map.filePath);
					$('#buddhaMusicPath_b').val(data.map.filePath);
					$('#buddhaMusicDurTime').val(data.map.buddhaMusicDurTime);
					
				} else {
					zUtils.error(data.message);
				}
		},
		error : function(e) {
			zUtils.error("上传失败");
		}
	});
}





