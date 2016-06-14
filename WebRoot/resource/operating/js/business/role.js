top.hangge();

// 检索
function search(pageNum) {

    var size = $('#pageSize option:selected').val();
    
    $("#ajaxPager").zPager({   
	target : $("#data_list"),	
	template : $("#data_temp"),
	url : BASE.basePath + "/roleBack/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#search_form').serialize(),
	id : "role_{roleId}",
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

// 新增/修改界面
function editor(id) {
	var url = BASE.basePath + '/roleBack/toEdit' + (zUtils.isUndefined(id) ? "" : "?roleId=" + id);
	var title = zUtils.isUndefined(id) ? "新增" : "修改";
	layerOpen(url,title);
}

// 删除
function del(roleId, msg) {
	layer.confirm("确定要删除[" + msg + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"删除角色"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/roleBack/delete',
			data : {
			    'roleId' : roleId
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
				    $('#role_' + roleId).fadeOut(500, function() {
						$(this).remove();
						$('#totalCount').text(parseInt($('#totalCount').text()) - 1);
				    });
				}else{
					layer.msg(rm.message, {icon: 2});
				}
			}
		});
	});
	
}

// 下拉框
$(".chzn-select").chosen({
    disable_search_threshold : 10
});

/** 新增/修改页面 * */

// 渲染提交表单
function initEditor() {
    $('#saveForm').zAction({
	url : BASE.basePath + "/roleBack/save",
	method : 'POST',
	before : function() {
	    layer.load(2);
	},
	callback : function(rm) {
		layerAlert(rm,"提示");
	    if (rm.result) {
	    	parent.search(1);
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
    parent.layer.closeAll();
}
