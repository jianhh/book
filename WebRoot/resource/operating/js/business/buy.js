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
    
    $("#ajaxPager").zPager({
	target : $("#data_list"),
	template : $("#data_temp"),
	url : BASE.basePath + "/buyBack/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#searchForm').serialize(),
	field : "name",
	isReplaceHtml:true,
	pageNumber : pageNum || 1,
	pageSize : size || 10,
	isReplaceHtml:true,
	callback : function(rm) {
	    if (rm.result) {
	    	$('#totalCount').text(rm.pager.totalCount);
	    }
	}
    });
}
