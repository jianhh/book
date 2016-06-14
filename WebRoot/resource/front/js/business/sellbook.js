$(function(){
	$(".fake-checkbox").click(function(){
		if($(this).hasClass("selected")){
			
			$(this).removeClass("selected");
		}else{
			$(".selected").removeClass("selected");
			$(this).addClass("selected");
		}
	});
	
	//支付
	$(".go-cashier-btn").click(function(){
		var total = $(".selected").size();
		if(total==0 || total<0){
			layer.msg("抱歉，您没有选中书本哦");
		}else{
			//获取书本信息
			var id = $(".selected").eq(0).attr("dataid");
			var url = '/user/toSell?id=' + id;
			
			layer.open({
				title:"出售书籍",
			    type: 2,
			    area: ['765px', '490px'],
			    fix: false, //不固定
			    maxmin: true,
			    zIndex:999,
			    moveType: 1,
			    content: url
			});
			//114494
		}
		
	});
	
	//确认收货地址
	$("#address-list li").click(function(){
		$("#address-list li").removeClass("selected");
		$(this).addClass("selected");
	});
	
	//添加收货地址
	$("#J_NewAddressBtn").click(function(){
		layer.open({
			title:"添加收货地址",
		    type: 2,
		    area: ['680px', '410px'],
		    fix: false, //不固定
		    maxmin: true,
		    zIndex:999,
		    moveType: 1,
		    content: "/index/confirmOrder"
		});
	});
});

//外网与本地查询分页
function search(pageNum) {
	var key = $("#search_key").val();
	$("input[name='key']").val(key);
	//启动加载层
	layer.msg('疯狂加载中...', {icon: 16,time:20000});
    $.post(BASE.basePath + "/search/getPager",$('#search_form').serialize(),function(data){
    	//关闭加载层
    	layer.closeAll();
    	//没有数据，显示友好提示，同时清空原有数据
    	if(!data.result){
    		$(".no-msg").css("display","block");
    		var list = $("#data_list");
        	list.empty();
    		return;
    	}
    	//隐藏友好提示
    	$(".no-msg").css("display","none");
    	//填充数据
    	var list = $("#data_list");
    	//清空上次分页信息
    	list.empty();
    	$.each(data.pager.list,function(i,b){
    		//填充模板信息
    		var temp = $("#data_temp").clone(true);
    		temp.attr("id","book_"+b.bookid);
    		temp.find("[data-id]").attr("href",temp.find("[data-id]").attr("href")+b.bookid);
    		temp.find("[data-title]").attr("title",b.bookname);
    		temp.find("[data-author]").text("作者："+b.bookauthor);
    		temp.find("[data-price]").text("￥"+b.bookoldprice);
    		temp.find("[data-img]").attr("src",b.bookpicpath);
    		temp.find("[data-publish]").text(b.bookpublish);
    		temp.find("[data-time]").text(b.bookpublishtime);
    		temp.find("[data-name]").text("书名："+b.bookname);
    		temp.find("[dataid]").attr("dataid",b.bookid);
    		//设置默认信息
    		if(b.bookisbn==null){
    			temp.find("[data-isbn]").text("暂无");
    		}else{
    			temp.find("[data-isbn]").text(b.bookisbn);
    		}
    		
    		temp.css("display","");
    		list.append(temp);
    	});
    	//启用laypage分页
    	laypage({
	        cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
	        skin: '#53b856',
	        groups: 5 ,//连续显示分页数
            pages: data.pager.pageCount, //通过后台拿到的总页数
            curr: pageNum || 1, //当前页
            jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                    search(obj.curr);
                    $("html,body").animate({
    					scrollTop : $(".body").offset().top - 60
    				}, 800);
                }
            }
	    });
    });
}