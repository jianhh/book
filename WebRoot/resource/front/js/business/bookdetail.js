$(function(){
	
	//生成验证码  
	$('#kaptchaImage').click(function () {
	  $(this).hide().attr('src','/Kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); 
	});

	$('.changeVerifyCode').click(function () {
		$('#kaptchaImage').hide().attr('src','/Kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); 
	});
	
	
	$(".fbpl").click(function(){
		var content = $(".comment-content-sub").val();
		var checkcode = $("#checkcode").val();
		var bookid = $("input[name='bookid']").val();
		$.post("/book/doComment",{commentbookid:bookid,commentcontent:content,checkcode:checkcode},function(data){
			if(data.result){
				layer.msg("评论成功");
				window.location.reload();
			}else{
				layer.msg(data.message);
			}
		});
		
	});
	
	//收藏 or 取消收藏
	$(".collectbook").click(function(){
		var id = $(this).attr("dataid");
		$.post("/book/collect",{id:id},function(data){
			if(data.result){
				layer.msg("操作成功");
				if(data.state=='false'){
					$(".collectbook div").text("收藏");
				}else{
					$(".collectbook div").text("取消收藏");
				}
			}else{
				layer.msg(data.message);
			}
			
		});
	});
	
});

//评论分页信息
function search(pageNum) {
	
    $.post(BASE.basePath + "/book/getCommentPager",$('#searchForm').serialize(),function(data){
    	//
    	if(!data.result){
    		var list = $("#data_list");
        	list.empty();
        	$(".cdj-color-2").text("0");
    		return;
    	}
    	$(".cdj-color-2").text(data.pager.totalCount);
    	//填充数据
    	var list = $("#data_list");
    	list.empty();
    	$.each(data.pager.list,function(i,b){
    		
    		var temp = $("#data_temp").clone(true);
    		temp.attr("id","");
    		temp.find("[data-con]").text(b.commentcontent);
    		temp.find("[data-author]").text(b.username);
    		temp.find("[data-pic]").attr("src",b.userpic);
    		temp.find("[data-time]").html("发表于 <font color='#6DA747'> "+b.commenttime+"</font>");
    		
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
    					scrollTop : $("#pl").offset().top - 60
    				}, 800);
                }
            }
	    });
    });
}

//卖家分页信息
function searchSeller() {
	var num = $("#sellerPageNum").val();
	if(num=="" || num==null || typeof(num)=='undefined'){
		num = 1;
	}
	$.post(BASE.basePath + "/book/getSellerPager",{pageNumber:num,pageSize:3,bookid:$("input[name='bookid']").val()},function(data){
		//
		if(!data.result){
			//改变信息
			$(".moreseller-btn span").text("已经到底了");
			return;
		}
		$("#sellerPageNum").val(num*1+1);
		$(".moreseller-btn span").text("查看更多卖家");
		//填充数据
		var list = $("#sell-table");
		$.each(data.pager.list,function(i,b){
			
			var temp = $("#seller_temp").clone(true);
			temp.attr("id","");
			temp.find("td").eq(0).text(b.userVo.schoolname);
			temp.find("td").eq(1).text(b.userVo.collegename);
			temp.find("td").eq(2).text(b.userVo.username);
			temp.find("td").eq(3).text(b.num-b.sellnum);
			temp.find("td").eq(4).text(b.price);
			temp.find("td").eq(5).find(".look-remark").attr("data-remark",b.remark);
			temp.find("td").eq(5).find(".look-remark").attr("id","book"+b.sellid);
			temp.find("td").eq(6).find(".buybtn").attr("data-seller",b.sellid);
			
			temp.css("display","");
			list.append(temp);
		});
	});
}

//查看备注
$(".look-remark").on("click",function(){
	var remark = $(this).attr("data-remark");
	var id = $(this).attr("id");
	//备注提示
	if(remark==""){
		remark = "暂无备注";
	}
	layer.tips(remark, "#"+id, {
		tips: [2, '#53b856']
	})
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


//加入书袋
$(".buybtn").click(function(){
	var id = $(this).attr("data-seller");
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
