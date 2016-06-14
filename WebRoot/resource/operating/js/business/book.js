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
	url : BASE.basePath + "/bookBack/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#searchForm').serialize(),
	id : "book_{bookid}",  
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



/**
 * 修改界面
 *
 */
function editor(id) {
	   
	var url = BASE.basePath + '/bookBack/toEdit' + (zUtils.isUndefined(id) ? "" : "?bookid=" + id);
	var title = zUtils.isUndefined(id) ? "新增" : "修改";
	layerOpen(url,title);
}

/**
 * 预览
 *
 */
function toView(id) {
  
	var url = BASE.basePath + '/bookBack/toView' + (zUtils.isUndefined(id) ? "" : "?id=" + id);
	var title = "用户管理";
	layerOpen(url,title);
}

// 删除
function del(bookid, msg) {
	
	
	layer.confirm("确定要删除[" + msg + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"删除书本"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/bookBack/doDelete',
			data : {
				'id' : bookid
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					$("#book_"+bookid).remove();
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
		
		layer.msg("请选择要删除的书籍！");
		
        return false;
    }
	
	layer.confirm("确定要删除[" + "选中的书籍" + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"批量删除书籍"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/bookBack/doDelete',
			data : {
				'id' : checkedList.toString()
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					
					//循环选中的input，逐个移除列表数据
					strParameters.each(function() {
						
						var bookId = $(this).val();
						
						$('#book_' + bookId).fadeOut(200, function() {
							 
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
	
    $('#saveForm').zAction({
	url : BASE.basePath + "/bookBack/save",
	method : 'POST',
	before : function() {
		layer.load(2);
	},
	callback : function(rm) {
		layerAlert(rm,"提示");
	    if (rm.result) {
	    	if($("input[name='bookid']").val()!=""){
	    		return;
    		}
	    	parent.search(1);
	    } 
	}
    });
    
}

//保存
function save() {
	if($('#art-face').val()==''){
		layer.msg("图片必须上传!",{icon:2});
		return false;
	}
	
	$('#saveForm').submit();
	return false;
}

// 取消
function cancel() {
    parent.layer.close();
}

//上传----------------------------------------------------------------------------

//face upload;
!function() {
var uploader;
uploader = WebUploader.create({
	  // swf文件路径
	  swf: '/resource/common/js/webuploader/Uploader.swf',
	
	  // 文件接收服务端。
	  server: BASE.basePath+'/fileUpload/doPicUpload',
	  pick: {id: '#bookpic',multiple: false},//'#upload-picker',
    resize: false,
    fileVal:'photo',
    fileNumLimit: 1,
    fileSize: 5 * 1024 * 1024,//2M
    auto: true,
    sendAsBinary: false,
    accept: {
      extensions: 'jpg,jpeg,gif,png,bmp'
    }
});
uploader.on('beforeFileQueued', function(file){
  //webupload 在只传一个文件的情况下，不能重选
  //手动清空队列，进行重选
  //目录版本里可以用uploader.reset 重置queue
  var files = uploader.getFiles(),
    len = files.length;
  while (len--) {
    uploader.removeFile(files[len], true);
  }
})
uploader.on('fileQueued', function(file) {
  err('');
});
uploader.on('error', function(msg) {
  var m = {
    'Q_EXCEED_NUM_LIMIT': '一次只能上传一张图片',
    'Q_EXCEED_SIZE_LIMIT': '图片不能超过2M',
    'Q_TYPE_DENIED': '图片类型不支持'
  }
  //console.log(msg)
  err(m[msg] || '文件格式不支持！');
});
uploader.on('uploadAccept', function(a,b){
  if (!b.result) {
    err(b.msg || '上传错误，稍后重试~');
  }else {
    $('#js-face-reault').html('<div class="face-result-inner"><img src="' + b.o.uploadfilepath + '" width="120"><span class="rm-face-reault">删除</span></div>');
    $('#art-face').val(b.o.uploadid);
  }
});
uploader.on('uploadStart', function() {
  err('正在上传...');
});

uploader.on('uploadError', function(file,error) {
  err('上传错误，请稍后重试');
  //uploader.stop();
  //uploader.reset();
});
uploader.on('uploadComplete', function() {
  //err('');
  uploader.reset();
});
function err(msg) {
  $('#js-face-reault').text(msg);
}

$('#js-face-reault').on('click', '.rm-face-reault', function() {
  $('#js-face-reault').empty();
  $('#art-face').val('');
  uploader.reset();
});
}();
