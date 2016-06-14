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
	url : BASE.basePath + "/operater/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#searchForm').serialize(),
	id : "operater_{operaterId}",
	field : "name",
	pageNumber : pageNum || 1,
	pageSize : size || 10,
	callback : function(rm) {
	    if (rm.result) {
		$('#totalCount').text(rm.pager.totalCount);
		$.each(rm.pager.list, function() {			
		    $('#operater_' + this.operaterId).find('.ace-switch').prop('checked', this.operaterIsEnable == 1);
		});
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
    diag.URL = BASE.basePath + '/operater/toEditor' + (zUtils.isUndefined(id) ? "" : "?operaterId=" + id);
    diag.Width = 750;
    diag.Height = 500;
    diag.CancelEvent = function() { // 关闭事件

	diag.close();
    };
    diag.show();
}


// 删除
function del(bjId, msg) {
    bootbox.confirm("确定要删除[" + msg + "]吗?", function(result) {
	if (result) {
	    $.ajax({
		type : "POST",
		url : BASE.basePath + '/operater/doDelete',
		data : {
		    'operaterId' : operaterId
		},
		success : function(rm) {
		    zUtils.message(rm, function() {
			if (rm.result) {
			    $('#operater_' + operaterId).fadeOut(500, function() {
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
    	zUtils.alert("请选择要删除的运营用户！");
        return false;
    }
	
    bootbox.confirm("确定要删除[" + "选中的运营用户" + "]吗?", function(result) {
		if (result) {
		    $.ajax({
			type : "POST",
			url : BASE.basePath + '/operater/doDelete',
			data: {
				'operaterId':checkedList.toString()
				}, 
			success : function(rm) {				
			    zUtils.message(rm, function() {
				if (rm.result) {
					//循环选中的input，逐个移除列表数据
					strParameters.each(function() {
						
						var operaterId = $(this).val();
						
						 $('#operater_' + operaterId).fadeOut(500, function() {
							 
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

//启用/停用
function enable(obj) {
    
	var value = 0;
	if ($(obj).prop('checked')) {
		value = 1;
	}
	
    
	$.ajax({
		type : "POST",
		url : BASE.basePath + '/operater/enable',
		data : {
			'operaterId' : $(obj).val(),
			'value' : value
		},
		success : function(rm) {
			
			zUtils.message(rm, function() {
				if (!rm.result) {
					if (value == 0) {
						$(obj).prop('checked', true);
					} else {
						$(obj).prop('checked', false);
					}
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
	url : BASE.basePath + "/operater/doSave",
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

