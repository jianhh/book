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
	url : BASE.basePath + "/medicineBroken/doList",
	basePath : BASE.basePath,
	method : "POST",
	parameter : $('#search_form').serialize(),
	id : "broken_{brokenId}",
	field : "name",
	isReplaceHtml:true,
	pageNumber : pageNum || 1,
	pageSize : size || 10,
	callback : function(rm) {
	    if (rm.result) {
	    	$('#totalCount').text(rm.pager.totalCount);
	    	$.each(rm.pager.list, function() {	
			    $('#broken_' + this.brokenId).find('.ace-switch').prop('checked', this.brokenIsPass == "已通过");
			});
	    }
	}
    });
}

function searchBroken(pageNum) {
	var size = $('#pageSize option:selected').val();
	
	$("#ajaxPager").zPager({
		target : $("#data_list"),
		template : $("#data_temp"),
		url : BASE.basePath + "/medicineBroken/doBrokenMedicineList",
		basePath : BASE.basePath,
		method : "POST",
		parameter : $('#saveForm').serialize(),
		id : "bm_{brokenId}",
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
	layer.open({
		title:zUtils.isUndefined(id) ? "新增" : "修改",
	    type: 2,
	    fix: false, //不固定
	    area: ['750px', '400px'],
	    maxmin: true,
	    zIndex:999,
	    moveType: 1,
	    content: BASE.basePath + '/medicineBroken/toEditor' + (zUtils.isUndefined(id) ? "" : "?id=" + id)
	});
}

/**
 * 选药
 *
 */
function selectMedicine(id) {
	
	layer.open({
		title:"选药",
	    type: 2,
	    closeBtn:1,
	    area: ['750px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    zIndex:999,
	    moveType: 1,
	    content: BASE.basePath + '/medicineBroken/toBrokenMedicineSelectList' + (zUtils.isUndefined(id) ? "" : "?id=" + id),
	    cancel: function(index){
	    	var medicineList = $("#medicineList").val();
	    	$("#medicineList").val("");
	    	if(medicineList!=""){
	    		layer.confirm("是否保存已选中的药", {icon: 3, title:'提示'}, 
	    		function(index, layero){
					var arr = new Array();
					if(medicineList.indexOf(",")){
						arr = medicineList.split(",");
					}else{
						arr.push(medicineList);
					}
					$.each(arr,function(i,value){
						// id,name,code,price,num
						var obj = $("#data_temp").clone();
						var m = value.split("#");
						var nu = $("input[value='"+m[0]+"']").val();
						if(typeof(nu)=='undefined'){
							obj.attr("id","medicine_"+m[0]);
							obj.find("input[name='medicineId']").val(m[0]);
							obj.find("td[name='medicinename']").text(m[1]);
							obj.find("td[name='medicineProductCode']").text(m[2]);
							obj.find("td[name='medicinePrice']").text(m[3]);
							obj.find("input[name='num']").attr("value",m[4]);
							$("#data_list").append(obj).find("#medicine_"+m[0]).show();
						}else{
							var number = $("input[value='"+m[0]+"']").next().find("input").val();
							$("input[value='"+m[0]+"']").next().find("input").val(number*1+m[4]*1);
						}
						
					});
					layer.close(index);
		    	}, function(index){
		    		layer.close(index);
		    	});
	    	}else{
	    		layer.close(index);
	    	}
	    },
	    end:function(){
	    	//获取子页面的参数
	    	var medicineList = $("#medicineList").val();
	    	$("#medicineList").val("");
	    	if(medicineList!=""){
				var arr = new Array();
				if(medicineList.indexOf(",")){
					arr = medicineList.split(",");
				}else{
					arr.push(medicineList);
				}
				$.each(arr,function(i,value){
					
					// id,name,code,price,num
					var obj = $("#data_temp").clone();
					var m = value.split("#");
					
					var nu = $("input[value='"+m[0]+"']").val();
					if(typeof(nu)=='undefined'){
						obj.attr("id","medicine_"+m[0]);
						obj.find("input[name='medicineId']").val(m[0]);
						obj.find("td[name='medicinename']").text(m[1]);
						obj.find("td[name='medicineProductCode']").text(m[2]);
						obj.find("td[name='medicinePrice']").text(m[3]);
						obj.find("input[name='num']").attr("value",m[4]);
						$("#data_list").append(obj).find("#medicine_"+m[0]).show();
						$("#data_list").append(obj);
					}else{
						var number = $("input[value='"+m[0]+"']").next().find("input").val();
						$("input[value='"+m[0]+"']").next().find("input").val(number*1+m[4]*1);
					}
					
				});
	    	}
	    }
	});
}

//取消
function cancel() {
    parent.layer.closeAll(); 
}

//下拉框
$(".chzn-select").chosen({
    disable_search_threshold : 10
});

//删除
function del(brokenId, msg) {
	
	layer.confirm("确定要删除[" + msg + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"删除"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/medicineBroken/doDel',
			data : {
				'id' : brokenId
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					$("#broken_"+brokenId).remove();
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
		if($(this).val()!=null && $(this).val().trim()!=""){
			layer.msg("请选择要删除的记录！");
		}
     return false;
    }
	
	layer.confirm("确定要删除[" + "选中的记录" + "]吗?", {
	    btn: ['确定','取消'], //按钮
		title:"批量删除"
	}, function(){
		$.ajax({
			type : "POST",
			url : BASE.basePath + '/medicineBroken/doDel',
			data : {
				'id' : checkedList.toString()
			},
			success : function(rm) {
				if (rm.result) {
					layer.msg(rm.message, {icon: 1});
					//循环选中的input，逐个移除列表数据
					strParameters.each(function() {
						var medicineid = $(this).val();
						$('#broken_' + brokenId).fadeOut(200, function() {
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

//渲染提交表单
function initEditor() {
	
 $('#saveForm').zAction({
	url : BASE.basePath + "/medicineBroken/doEditor",
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

//保存
function save() {
	
	$('#saveForm').submit();
}


//渲染上传控件
if ('zUpload' in $('#advertisePic')) {	    
	    var zUpload = $('#advertisePic').zUpload({
		url : BASE.basePath + '/medicineManager/doUpload',
		fieldName : 'myFile',
		fileSize : 2 * 1024 * 1024,
		fileCount : 9,
		multiple : true,		
		success : function(data) {			
				if (data.result == true) {	
					$('#imgresNewPath').closest('.info').show();					
					$('#imgresNewPath').attr('src',BASE.basePath + data.map.filePath).show();
					$('#advertisePic1').val(data.map.filePath);				
				} else {
					layer.msg(data.message,{icon:2});
				}
		},
		error : function(e) {
			layer.msg("上传失败!",{icon:2});
		}
	});
}

function changeHeight(obj){
	
	var h = $(obj).attr("oh");
	if(h==""){
		$(obj).attr("oh",$(obj).find("div").css("height"));
		$(obj).find("div").css("height","");
	}else{
		$(obj).find("div").css("height",$(obj).attr("oh"));
		$(obj).attr("oh","");
	}
}
//启用/停用
function enable(obj) {
    
	var value = "未通过";
	if ($(obj).prop('checked')) {
		value = "已通过";
	}
    
	$.ajax({
		type : "POST",
		url : BASE.basePath + '/medicineBroken/enable',
		data : {
			'id' : $(obj).val(),
			'value' : value
		},
		success : function(rm) {
			if(!rm.result){
				if (value == "未通过") {
					$(obj).prop('checked', true);
				} else {
					$(obj).prop('checked', false);
				}
				layer.msg("修改失败",{icon:2});
			}else{
				layer.msg("修改成功",{icon:1});
			}
		}
	});
}


function removeBroken(obj){
	layer.confirm("确定要移除该 药品 ?", {
	    btn: ['确定','取消'], //按钮
		title:"操作"
	}, function(index){
		$(obj).parent().parent().parent().remove();
		layer.close(index);
	});
}

function generateAndCondition(name,value){
	var html = '<input type="hidden" name="innerPPAndRss" value="'+name+'"/><input type="hidden" name="innerKeyOrValue" value="'+value+'" />';
	return html;
}


