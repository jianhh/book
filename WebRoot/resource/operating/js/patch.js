/**
 * 对UI的补丁
 */

// 全选框补丁
// 复选框
$(document).on('click', 'table tr th:first-child :checkbox:visible', function(e) {
    var that = this;
    $(this).closest('table').find('tr td:first-child input:checkbox').each(function() {
	this.checked = that.checked;
	$(this).closest('tr').toggleClass('selected');
    });

});

$(document).on('click', 'table tr td:first-child :checkbox:visible', function(e) {
    var _all_chk_ = $(this).closest('table').find('tr th:first-child :checkbox:visible');

    if ($(this).is(':checked')) {
	var _chks_ = $(this).closest('table').find('tr').find('td:first-child :checkbox:visible');
	if (_chks_.length == _chks_.filter(':checked').length) {
	    _all_chk_.prop('checked', true);
	}
    } else {
	_all_chk_.prop('checked', false);
    }
});
