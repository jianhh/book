$("#clear-content").click(function(){
	$("#search_key").val("");
});

$("#search_btn").click(function(){
	var key = $("#search_key").val();
	$("input[name='innerKeyOrValue']").val(key);
	search();
});


//加入书袋
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
	
});

//改变查询文字的颜色
function addColorForKeyWord(str,keyword){
	if(isBlank(keyword)){
		return str;
	}
	if(str.indexOf(keyword)<0){
		return str;
	}
	
	var curindex = str.indexOf(keyword);
	var curstr = str;
	var result = "";
	while(curstr.indexOf(keyword)>-1){
		var i = curstr.indexOf(keyword);
		result = curstr.substring(0,i)+"<font style='color:#F01400;'>"+keyword+"</font>";
		curstr = curstr.substring(i*1+keyword.length);
	}
	result = result+curstr;
	return result;
}

//分页
function search(pageNum) {
	
	if(pageNum!="" && typeof(pageNum)!='undefined'){
		$("input[name='pageNumber']").val(pageNum);
	}
    $.post(BASE.basePath + "/book/getPager",$('#search_form').serialize(),function(data){
    	//
    	if(!data.result){
    		layer.msg("没有数据");
    		var list = $("#data_list");
    		$("#totalBook").text("0");
        	list.empty();
        	$("#pager").css("display","none");
    		return;
    	}
    	$("#pager").css("display","block");
    	$("#totalBook").text(data.pager.totalCount);
    	//填充数据
    	var list = $("#data_list");
    	list.empty();
    	var key = $("input[name='innerKeyOrValue']").val();
    	$.each(data.pager.list,function(i,b){
    		
    		var temp = $("#data_temp").clone(true);
    		temp.attr("id","book_"+b.bookid);
    		temp.find("[data-id]").attr("href",temp.find("[data-id]").attr("href")+b.bookid);
    		temp.find("[data-intro]").html(addColorForKeyWord($.trim(subcon(b.bookintro,80)),key));
    		temp.find("[data-author]").html(addColorForKeyWord(b.bookauthor,key));
    		temp.find("[data-price]").text("￥"+b.bookoldprice);
    		temp.find("[data-name]").html(addColorForKeyWord(subcon(b.bookname,80),key));
    		temp.find("[data-img]").attr("src",b.bookpicpath);
    		temp.find("[data-publish]").html(addColorForKeyWord("( "+b.bookpublish+"&nbsp;&nbsp;"+b.bookpublishtime+" )",key));
    		temp.find("[dataid]").attr("dataid",b.bookid);
    		/*if(b.booknum<1){
    			temp.find(".buybook").css("display","none");
    			temp.find(".sellbook").css("display","block");
    		}*/
    		
    		temp.css("display","");
    		list.append(temp);
    	});
    	
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
//判断是否为空
function isBlank(str){
	if(str==null || str=="" || typeof(str)=='undefined'){
		return true;
	}
	return false;
}

//截取字符个数
function subcon(str,length){
	if(str==null || str=="" || typeof(str)=='undefined'){
		return "暂无";
	}
	if(str.length<=length){
		return str;
	}
	return str.substring(0,length)+"...";
	
}
//按enter键
$(':input').keyup(function(event) {
    if (event.keyCode == 13) {
    	$("#search_btn").trigger("click");
    }
});

//排序查询
$(".search-toolbar a").click(function(){
	
	$(".search-toolbar a").removeClass("cur");
	if($(this).index()==0){
		$(this).addClass("cur");
		$("input[name='orderBy']").val(" ");
	}else if($(this).index()==1){
		$(this).addClass("cur");
		$("input[name='orderBy']").val(" bookpv DESC ");
	}else if($(this).index()==2){
		
		if($(this).hasClass("curup")){
			$(".search-toolbar a").removeClass("curup");
			$(this).addClass("curdown");
			$("input[name='orderBy']").val(" BOOKOLDPRICE DESC ");
		}else if($(this).hasClass("curdown")){
			$(".search-toolbar a").removeClass("curdown");
			$(this).addClass("curup");
			$("input[name='orderBy']").val(" BOOKOLDPRICE ");
		}else{
			$(".search-toolbar a").removeClass("curup");
			$(".search-toolbar a").removeClass("curdown");
			$(this).addClass("curup");
			$("input[name='orderBy']").val(" BOOKOLDPRICE ");
		}
	}else if($(this).index()==3){
		
		if($(this).hasClass("curup")){
			$(".search-toolbar a").removeClass("curup");
			$(this).addClass("curdown");
			$("input[name='orderBy']").val(" bookaddtime DESC ");
		}else if($(this).hasClass("curdown")){
			$(".search-toolbar a").removeClass("curdown");
			$(this).addClass("curup");
			$("input[name='orderBy']").val(" bookaddtime ");
		}else{
			$(".search-toolbar a").removeClass("curup");
			$(".search-toolbar a").removeClass("curdown");
			$(this).addClass("curup");
			$("input[name='orderBy']").val(" bookaddtime ");
		}
	}
	search(1);
});

//出售
$(".sellbook").click(function(obj){
	//获取书本信息
	var id = $(this).attr("dataid");
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
});