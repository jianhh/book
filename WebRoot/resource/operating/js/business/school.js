top.hangge();

// 设置控件
var setting = {
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	edit : {
		enable : true,
		editNameSelectAll : true,
		showRemoveBtn : showRemoveBtn,
		showRenameBtn : showRenameBtn
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeDrag : beforeDrag,
		beforeEditName : beforeEditName,
		beforeRemove : beforeRemove,
		beforeRename : beforeRename,
		onRemove : onRemove,
		onRename : onRename
	}
};

function beforeDrag(treeId, treeNodes) {
	return false;
}

function showRenameBtn(treeId, treeNode) {
	return true;
}

function beforeEditName(treeId, treeNode) {	
	zTree.selectNode(treeNode);
	//return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
	return true;
}

function beforeRename(treeId, treeNode, newName, isCancel) {

	if (newName.length == 0) {
		layer.msg("节点名称不能为空");
		return false;
	}

	$.ajax({
		type : "POST",
		url : BASE.basePath + '/schoolBack/doEditor',
		data : {
			'dicid' : treeNode.id,
			'dicremark' : treeNode.pId,
			'dicname' : newName
		},
		success : function(rm) {
			if (rm.result) {
				layer.msg("修改成功", {icon: 1});
			} else {
				layer.msg(rm.message, {icon: 2});
				zTree.editName(treeNod);
			}
		}
	});

	return true;
}

function onRename(e, treeId, treeNode, isCancel) {

}

function showRemoveBtn(treeId, treeNode) {
	return !treeNode.isParent;
}

function beforeRemove(treeId, treeNode) {
	zTree.selectNode(treeNode);
	
	layer.confirm("确定要删除[" + treeNode.name + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"删除分类"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/schoolBack/doDelete',
			data : {
				'dicid' : treeNode.id
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					zTree.removeNode(treeNode);
				}else{
					layer.msg(rm.message, {icon: 2});
				}
			}
		});
	});

	return false;
}

function onRemove(e, treeId, treeNode) {
	layer.msg(treeNode);
}

function addHoverDom(treeId, treeNode) {
	/*if(treeNode.father != "1" && treeNode.father !=""){
		return false;
	}*/
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);

	var btn = $("#addBtn_" + treeNode.tId);
	if (btn) {
		btn.bind("click", function() {

			$.ajax({
				type : "POST",
				url : BASE.basePath + '/schoolBack/add',
				data : {
					'dicremark' : treeNode.id,
					'dicname' : "未命名"
				},
				success : function(rm) {					
					if (rm.result) {												
						var nodes = zTree.addNodes(treeNode, {
							id : rm.o.dicid,
							pId : rm.o.dicremark,
							name : rm.o.dicname
						});
						zTree.editName(nodes[0]);
					} else {
						layer.msg(rm.message, {icon: 2});
					}
				}
			});
			return false;
		});
	}
};

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
};

$.fn.zTree.init($("#treeDemo"), setting, zNodes);
var zTree = $.fn.zTree.getZTreeObj("treeDemo");


/**
 * 查询触发事件
 */

$('#keyword').keyup(
		function(e) {
			if (e.which == 40 || e.which == 38 || e.which == 13) {
				return;
			}

			var word = this.value;

			if ($.trim(word) == "") {
				$('#search_result_list').hide();
				return;
			}

			var allNodes = zTree.transformToArray(zTree.getNodes());
			var html = new StringBuilder();

			for ( var i = 0; i < allNodes.length; i++) {
				if (allNodes[i].name.indexOf(word) > -1) {
					html.append('<li onclick="locateNode(\'' + allNodes[i].id
							+ '\')">' + allNodes[i].name + '</li>');
				}
			}

			if (html.toString() != "") {
				$('#search_result_list').html(
						'<ul class="select-result">' + html.toString()
								+ '</ul>');
				$('#search_result_list').show();

				curr_index = -1;
			} else {
				$('#search_result_list').hide();
			}
		});

var curr_index;
$(document)
		.click(				
				function(e) {					
					if ($(e.target).closest('div')[0] != $('#search_result_list')[0]
							&& $(e.target)[0] != $('#keyword')[0]) {
						$('#search_result_list').hide();
					}
				})
		.keyup(
				function(e) {
					if ($('#search_result_list').is(':visible')) {
						// 键盘下
						if (e.which == 40) {
							if (curr_index >= $('#search_result_list ul li').length - 1) {
								curr_index = 0;
							} else {
								curr_index++;
							}

							$('#search_result_list ul li').removeClass(
									'result-li-hover').eq(curr_index).addClass(
									'result-li-hover');
							return;
						}

						// 键盘上
						if (e.which == 38) {
							if (curr_index <= 0) {
								curr_index = $('#search_result_list ul li').length - 1;
							} else {
								curr_index--;
							}

							$('#search_result_list ul li').removeClass(
									'result-li-hover').eq(curr_index).addClass(
									'result-li-hover');
							return;
						}

						// 回车键
						if (e.which == 13) {
							$('#search_result_list ul li').eq(curr_index)
									.click();
							$('#keyword').focus();
						}
					}
				});

// 定位到节点
function locateNode(id) {

	var self = zTree.getNodeByParam("id", id, null);

	zTree.selectNode(self);

	$('#search_result_list').hide();
}
