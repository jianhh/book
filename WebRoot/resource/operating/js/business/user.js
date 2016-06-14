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
	url : BASE.basePath + "/userBack/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#search_form').serialize(),
	id : "user_{userid}",
	field : "name",
	isReplaceHtml:true,
	pageNumber : pageNum || 1,
	pageSize : size || 10,
	callback : function(rm) {
	    if (rm.result) {
	    	$('#totalCount').text(rm.pager.totalCount);
	    	$.each(rm.pager.list, function() {
			    $('#user_' + this.userid).find('#isEnable').prop('checked', this.isenable == 'yes');
			});
	    }
	}
    });
    return false;
}


/**
 * 修改界面
 *
 */
function editor(id) {
   
	var url = BASE.basePath + '/userBack/toEdit' + (zUtils.isUndefined(id) ? "" : "?userId=" + id);
	var title = zUtils.isUndefined(id) ? "新增" : "修改";
	layerOpen(url,title);
}


function selectCollege(obj){
	var schoolid = $(obj).val();
	$.post(BASE.basePath + '/userBack/doListByFatherId',{fatherId:schoolid},function(rm){
		if(rm.result){
			$("#college").empty();
			var html = '<option value="">请选择学院</option>';
			$.each(rm.list,function(i,o){
				html = html + '<option value="'+this.dicid+'">'+this.dicname+'</option>';
			})
			$("#college").append(html);
			// 刷新css的渲染。否则不起作用
			$(".chzn-select").trigger("liszt:updated");
		}
	});
}

//渲染提交表单
function initEditor() {
	
    $('#saveForm').zAction({
		url : BASE.basePath + "/userBack/save",
		method : 'POST',
		data : $('#saveForm').serialize(),
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

//保存
function save() {
	if($('#art-face').val()==''){
		layer.msg("图片必须上传!",{icon:2});
		return false;
	}
	$('#saveForm').submit();
	return false;
}


/**
 * 预览
 *
 */
function toView(id) {
  
	var url = BASE.basePath + '/userBack/toPreview' + (zUtils.isUndefined(id) ? "" : "?userId=" + id);
	var title = "用户管理";
	layerOpen(url,title);
}
/**
 * 编辑
 *
 */
function toView(id) {
  
	var url = BASE.basePath + '/userBack/toPreview' + (zUtils.isUndefined(id) ? "" : "?userId=" + id);
	var title = "用户管理";
	layerOpen(url,title);
}


//取消
function cancel() {
    parent.layer.closeAll(); 
}

//下拉框
$(".chzn-select").chosen({
    disable_search_threshold : 10
});

//设定是不是可用
function enable(obj) {
	var value = "no";
	if ($(obj).prop('checked')) {
		value = "yes";
	}
	$.ajax({
		type : "POST",
		url : BASE.basePath + '/userBack/doEnable',
		data : {
			'id' : $(obj).val(),
			'value' : value,
		},
		success : function(rm) {
				if (!rm.result) {
					layer.msg(rm.message,{icon:2});
					if (value == "no") {
						$(obj).prop('checked', true);
					} else {
						$(obj).prop('checked', false);
					}
				}else{
					layer.msg(rm.message,{icon:1});
				}
		}
	});
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
	  pick: {id: '#userpic',multiple: false},//'#upload-picker',
      resize: false,
      fileVal:'photo',
      fileNumLimit: 1,
      fileSize: 2 * 1024 * 1024,//2M
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