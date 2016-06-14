var timer,hover=$(".book_hover_card_wrap"); 
//封面停1秒后弹出窗
function showbook(obj){
	var ts = $(obj);
	//设置定时器
    timer=setTimeout(function(){
    	
    	if(ts.find(".book_hover_card_wrap").size()===0){
    		//获取鼠标停留书籍信息
    		var html = hover.clone(true);
    		var intro = ts.find(".bookintro").attr("data-content");
    		var title = ts.find(".title").text();
    		var price = ts.find(".bookprice").attr("data-price");
    		var author = ts.find(".author").text();
    		var num = ts.attr("data-num");
    		var id = ts.attr("dataid");
    		//设置默认信息
    		if(intro=="" || typeof(intro)=='undefined' || intro.length<1){
    			intro = "暂无简介";
    		}
    		//复制参数到模板
    		html.find(".card_title").text(title);
    		html.find(".card_desp").html(intro);
    		html.find(".card_author").text(author);
    		html.find(".card_price").text("原价："+price+"元");
    		html.find("[dataid]").attr("dataid",id);
    		//设置最右侧的悬浮页悬浮在左边，避免页面撑开
    		if((ts.index()*1+1)%5==0 || (ts.index()*1+1)%5==4){
    			$(html).css("left","-280px");
    		}else{
    			$(html).css("left","150px");
    		}
    		//渲染模板
    		ts.append(html);
    	}
    	//上一次悬浮页隐藏
    	$(".book_hover_card_wrap").hide();
    	//本次悬浮页淡出
    	ts.find(".book_hover_card_wrap").fadeIn();
    	
    },600);   
    
}

function hidebook(book){
	if($(book).find(".book_hover_card_wrap").size()!=0){
		$(book).find(".book_hover_card_wrap").remove();
	}
    clearTimeout(timer);   
}

//分类
$(".cate-menu-box").hover(function(){
	$(".cate-menu-box").find(".tab-cate-hd").css({"background-color":"#F5F4F2"});
	$(this).find(".tab-cate-hd").css({"background-color":"#34495E","border-color":"#F5F4F2"});
	$(this).find(".tab-cate-hd a").css("color","#fff");
	$(this).find(".popup-cate").css("display","block");
},function(){
	$(this).find(".tab-cate-hd").css({"background-color":"#F5F4F2","border-color":"#F5F4F2"});
	$(this).find(".tab-cate-hd a").css("color","#333");
	$(this).find(".popup-cate").css("display","none");
});
	
function search(pageNum) {
	
	if(pageNum!="" && typeof(pageNum)!='undefined'){
		$("input[name='pageNumber']").val(pageNum);
	}
    $.post(BASE.basePath + "/book/getPager",$('#search_form').serialize(),function(data){
    	//
    	if(!data.result){
    		layer.msg("没有数据");
    		return;
    	}
    	$("#totalBook").text(data.pager.totalCount);
    	//填充数据
    	var list = $("#data_list");
    	list.empty();
    	$.each(data.pager.list,function(i,b){
    		var temp = $("#data_temp").clone(true);
    		temp.attr("id","book_"+b.bookid);
    		temp.attr("dataid",b.bookid);
    		temp.find("[data-id]").attr("href",temp.find("[data-id]").attr("href")+b.bookid);
    		temp.find("[data-content]").attr("data-content",b.bookintro);
    		temp.find(".author").text(b.bookauthor);
    		temp.find(".bookprice").text(b.booknum);
    		temp.find(".bookprice").attr("data-price",b.bookoldprice);
    		temp.find("[data-name]").text(b.bookname);
    		temp.find("[data-path]").attr("src",b.bookpicpath);
    		temp.attr("data-num",b.booknum);
    		
    		temp.css("display","");
    		list.append(temp);
    	});
    	
    	$(".defbook").error(function(){
		  $(this).attr("src","/resource/common/images/default_book.jpg");
		});
    	laypage({
	        cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
	        skip: true, //是否开启跳页
	        skin: '#53b856',
	        groups: 5 ,//连续显示分页数
            pages: data.pager.pageCount, //通过后台拿到的总页数
            curr: pageNum || 1, //当前页
            jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                    search(obj.curr);
                }
            }
	    });
    });
}

//顶部排序
$("#selectpx a").click(function(){
	//移除其他选中样式
	$("#selectpx a").removeClass("sort-select");
	//给自己加上选中状态
	$(this).addClass("sort-select");
	//根据位置添加表单排序信息
	if($(this).index()==0){
		$("input[name='orderBy']").val(" BOOKNUM DESC ");
	}else if($(this).index()==1){
		$("input[name='orderBy']").val(" bookpv DESC ");
	}else if($(this).index()==2){
		$("input[name='orderBy']").val(" BOOKADDTIME DESC ");
	}else if($(this).index()==3){
		//设置价格排序样式，同时设置排序
		if($(this).hasClass("sort-price-up-select")){
			$(this).removeClass("sort-price-up-select");
			$(this).addClass("sort-price-down-select");
			$("input[name='orderBy']").val(" BOOKOLDPRICE DESC ");
		}else if($(this).hasClass("sort-price-down-select")){
			$(this).addClass("sort-price-up-select");
			$(this).removeClass("sort-price-down-select");
			$("input[name='orderBy']").val(" BOOKOLDPRICE ");
		}else{
			$(this).addClass("sort-price-up-select");
			$("input[name='orderBy']").val(" BOOKOLDPRICE ");
		}
	}
	//使用公共分页
	search(1);
});

/*//加入书袋
$(".buybook").click(function(){
	var id = $(this).attr("dataid");
	$.post("/book/tobookpocket",{id:id},function(data){
		if(data.result){
			layer.msg("加入书袋成功");
			//成功后，修改右侧栏数量
			$("#bookpocketnum").text("书袋("+data.state+")");
			setTimeout(function(){
				$(".bookpocket").css({"background-color":"#53b856","color":"#fff"});
				$(".bookpocket .ic").css("display","none");
				$("#bookpocketnum").css("display","block");
			},300);
			setTimeout(function(){
				$(".bookpocket").css({"background":"#d1d1d1","color":""});
				$(".bookpocket .ic").css("display","block");
				$("#bookpocketnum").css("display","none");
			},600);
			setTimeout(function(){
				$(".bookpocket").css({"background-color":"#53b856","color":"#fff"});
				$(".bookpocket .ic").css("display","none");
				$("#bookpocketnum").css("display","block");
			},900);
		}else{
			layer.msg(data.message);
		}
	});
	
});*/

//出售
function sellBook(obj){
	//获取书本信息
	var id = $(obj).attr("dataid");
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
}

//分类异步
$(".inner .popup-cate li a").click(function(){
	var id = $(this).attr("data-id");
	//填写pager信息
	$("input[name='ppNames']").remove();
	$("input[name='keywords']").remove();
	$("#search_form").append('<input type="hidden" name="ppNames" value="booktypeid#=" /><input type="hidden" name="keywords" value="'+id+'" />');
	//共用search查询
	search();
	//上滚到顶部
	$("html,body").animate({
		scrollTop : $("#page-curmbs").offset().top - 60
	}, 400);
});

