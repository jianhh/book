if (!window.XMLHttpRequest) {

    // 解决IE6透明PNG图片BUG
    DD_belatedPNG.fix(".png");

    // 解决IE6不缓存背景图片问题
    document.execCommand("BackgroundImageCache", false, true);

}
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            serializeObj[this.name]=this.value;
        });  
        return serializeObj;  
    };  
})(jQuery); 

/**
 * jQuery ajax 全局设置
 */
jQuery.ajaxSetup({
    global : true,
    async : true,
    timeout : 1000 * 60 * 10,
    dataType : "json",
    type : "POST",
    error : function(data) {
	//zUtils.alert("请求超时");
    },
    dataFilter : function(data, type) {
	if (data.result == false && data.state == "reLogin") {
	    zUtils.alert(data.message, function() {
		window.location.href = BASE.basePath + "/yun/login";
	    });
	}
		return data;
    }
});
 
// 表单验证
//function formValidate(obj, func) {
//    if (!("validate" in obj)) {
//	return;
//    }
//
//    $(obj).validate({
//	errorClass : "validateError",
//	ignore : ".ignoreValidate",
//	errorPlacement : function(error, element) {
//
//	    var $msg = element.metadata().msg;
//	    if ("undefined" != typeof $msg && $msg != "") {
//		zUtils.alert($msg + error.text().replace("此内容", ""));
//	    } else {
//		var messagePosition = element.metadata().messagePosition;
//		if ("undefined" != typeof messagePosition && messagePosition != "") {
//		    var $messagePosition = $(messagePosition);
//		    if ($messagePosition.size() > 0) {
//			error.insertAfter($messagePosition).fadeOut(300).fadeIn(300);
//		    } else {
//			error.insertAfter(element).fadeOut(300).fadeIn(300);
//		    }
//		} else {
//		    error.insertAfter(element).fadeOut(300).fadeIn(300);
//		}
//	    }
//	},
//	submitHandler : function(form) {
//	    if (typeof (func) == 'function') {
//		func();
//	    } else {
//		form.submit();
//	    }
//	}
//    });
//};
function layerAlert(rm,title){
	if (rm.result) {
    	layer.alert(rm.message,{
    		icon:1,
    		title:title,
    		moveType:1,
    		cancel:function(index){
    			layer.closeAll('loading');
    			layer.close(index);}
    		},
    		function(){parent.layer.closeAll();});
    } else {
    	layer.alert(rm.message,{icon:2,title:title,moveType:1,cancel:function(index){layer.closeAll('loading');}},function(index){layer.closeAll('loading');layer.close(index);});
    }
}
/**
 * 编辑 添加弹出层
 * @param url
 * @param title
 */
function layerOpen(url,title){
	layer.open({
		title:title,
	    type: 2,
	    area: ['750px', '440px'],
	    fix: false, //不固定
	    maxmin: true,
	    zIndex:999,
	    moveType: 1,
	    content: url
	});
	
}
//图片放大  图片的 img 标签 增加 class ="fdPic" 即可
$(function(){
	$(".fdPic").live("click",function(){
		var picurl = $(this).attr("src");
		$("#tong").find("img").attr("src",picurl);
		
		layer.open({
		    type: 1,
		    title: false,
		    closeBtn: 1,
		    area: '800px',
		    zIndex:999,
		    skin: 'layui-layer-nobg', //没有背景色
		    shadeClose: true,
		    content: $("#tong"),
		});
	});
});
//图片放大
$(function(){
	$(".fdPic1").live("click",function(){
		var picurl = $(this).attr("src");
		$("#tong").find("img").attr("src",picurl);
		
		layer.open({
		    type: 1,
		    closeBtn: 1,
		    area: '600px',
		    zIndex:999,
		    skin: 'layui-layer-nobg', //没有背景色
		    shadeClose: true,
		    content: $("#tong"),
		});
	});
	
	//内容扩展缩小行
	$(".extend").live("click",function(){
		var h = $(this).attr("oh");
		if(typeof(h)=='undefined'){
			$(this).attr("oh","");
			h = "";
		}
		if(h==""){
			//其他关闭
			$(".extend").each(function(){
				var oh = $(this).attr("oh");
				if(oh!=""){
					$(this).parent().find(".extend").find("div").css("height",oh);
					$(this).parent().find(".extend").attr("oh","");
				}
				
			});
			
			$(this).parent().find(".extend").attr("oh",$(this).find("div").css("height"));
			$(this).parent().find(".extend").find("div").css("height","");
		}else{
			$(this).parent().find(".extend").find("div").css("height",$(this).attr("oh"));
			$(this).parent().find(".extend").attr("oh","");
		}
	});
});



/**
 *  当弹出框页面出现滚动条时,table加载样式,使其不下移太多
 */

$(document).ready(function(){
	
	if(document.documentElement.clientHeight < document.documentElement.offsetHeight){
		
		$("#saveForm .page-content").find("table").addClass("overflow");
		
	}
	
});




