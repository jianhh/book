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
	url : BASE.basePath + "/resourcesBack/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#search_form').serialize(),
	id : "resources_{uploadid}",
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
    return false;
}


/**
 * 预览
 *
 */
function toView(id) {
  
	var url = BASE.basePath + '/resourcesBack/toView' + (zUtils.isUndefined(id) ? "" : "?uploadid=" + id);
	var title = "资源管理";
	layerOpen(url,title);
}

//取消
function cancel() {
    parent.layer.closeAll(); 
}
