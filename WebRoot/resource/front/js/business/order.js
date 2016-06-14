$(function(){
	$(".select-all-book-wrap").click(function(){
		if($(this).find(".fake-checkbox").hasClass("selected")){
			$(".fake-checkbox").removeClass("selected");
			$(".js-selected-count").text("0");
			$(".cart-ft-total-price-txt").text("0.00");
		}else{
			$(".fake-checkbox").addClass("selected");
			
			//总数
			if($(".fake-checkbox").size()==$(".selected").size()){
				$(".js-selected-count").text($(".selected").size()-1);
			}else{
				$(".js-selected-count").text($(".selected").size());
			}
			//总价
			var totalprice = 0;
			
			$(".selected").each(function(i,o){
				var price = $(this).parent().parent().find(".bookprice").attr("data-price");
				if(!isNaN(price)){
					totalprice = (totalprice*1+price*1).toFixed(2);
				}
				
			});
			$(".cart-ft-total-price-txt").text(totalprice);
		}
	});
	
	$(".fake-checkbox").click(function(){
		if($(this).hasClass("selected")){
			
			$(this).removeClass("selected");
			//总数
			if($(".fake-checkbox").size()==$(".selected").size()){
				$(".js-selected-count").text($(".selected").size()-1);
			}else{
				$(".select-all-book-wrap").find(".fake-checkbox").removeClass("selected");
				$(".js-selected-count").text($(".selected").size());
			}
			//总价
			var totalprice = 0;
			
			$(".selected").each(function(i,o){
				var price = $(this).parent().parent().find(".bookprice").attr("data-price");
				if(!isNaN(price)){
					totalprice = (totalprice*1+price*1).toFixed(2);
				}
				
			});
			$(".cart-ft-total-price-txt").text(totalprice);
		}else{
			
			$(this).addClass("selected");
			if($(".fake-checkbox").size()==$(".selected").size()*1+1){
				$(".fake-checkbox").addClass("selected");
			}
			//总数
			if($(".fake-checkbox").size()==$(".selected").size()){
				$(".js-selected-count").text($(".selected").size()-1);
			}else{
				$(".js-selected-count").text($(".selected").size());
			}
			
			//总价
			var totalprice = 0;
			
			$(".selected").each(function(i,o){
				var price = $(this).parent().parent().find(".bookprice").attr("data-price");
				if(!isNaN(price)){
					totalprice = (totalprice*1+price*1).toFixed(2);
				}
				
			});
			$(".cart-ft-total-price-txt").text(totalprice);
		}
	});
	
	//支付
	$(".go-cashier-btn").click(function(){
		var total = $(".cart-ft-total-price-txt").text();
		if(total==0 || total<0){
			layer.msg("抱歉，您没有选中书本哦");
		}else{
			var arr = new Array();
			$.each($(".selected"),function(i,o){
				var id = $(this).attr("data-id");
				if(id==null || id=="" || typeof(id)=='undefined'){
					
				}else{
					$(this).prev().prop("checked",true);
					arr.push(id);
				}
			});
			
			$("#orderForm").submit();
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
		    content: "/area/toAddress"
		});
	});
	//修改收货地址
	$(".J_Modify").click(function(){
		var url = "/area/toAddress?id="+$(this).attr("data-id");
		layer.open({
			title:"添加收货地址",
		    type: 2,
		    area: ['680px', '410px'],
		    fix: false, //不固定
		    maxmin: true,
		    zIndex:999,
		    moveType: 1,
		    content: url
		});
	});
	//删除
	$(".deleteorder").click(function(){
		var id = $(this).attr("data-id");
		if(id==null || id=="" || typeof(id)=='undefined'){
			layer.msg("抱歉，提交参数有误，请刷新页面再试");
			return ;
		}
		
		$.post("/user/delPocket",{id:id},function(data){
			if(data.result){
				window.location.reload();
			}else{
				layer.msg(data.message);
			}
		});
		
	});
	//切换收货地址
	$(".J_Addr").click(function(){
		$("#address-list input").prop("checked",false);
		$(this).find("input").prop("checked",true);
		var addresspre = $(this).find(".addresspre").text();
		var consig = $(this).find(".consig").text();
		var pnumber = $(this).find(".pnumber").text();
		
		$(".J_BuyFooterAddressDetail").text(addresspre);
		$(".J_BuyFooterAddressRec").text(consig+" "+pnumber);
		
	});
	
	
	
	$(".del-select-book").click(function(){
		if($(".selected").size()==0){
			layer.msg("请选中需要删除的书籍");
			return;
		}
		var arr = new Array();
		$.each($(".selected"),function(i,o){
			var id = $(this).attr("data-id");
			if(id==null || id=="" || typeof(id)=='undefined'){
				
			}else{
				arr.push(id);
			}
		});
		
		$.post("/user/delPocket",{id:arr.toString()},function(data){
			if(data.result){
				window.location.reload();
			}else{
				layer.msg(data.message);
			}
		});
		
	});
	
	
	//支付
	$("#J_Go").click(function(){
		var orderid = $("input[name='orderid']").val();
		//地址id
		var addressid = $(".selected").find(".J_Modify").attr("data-id");
		var remarkarr = new Array();
		$.each($(".buyremark"),function(i,o){
			remarkarr.push($(this).val());
		});
		
		var buyid = new Array();
		$.each($("input[name='buyid']"),function(i,o){
			buyid.push($(this).val());
		});
		$.post("/user/pay",{id:buyid.toString(),remark:remarkarr.toString(),addressid:addressid},function(data){
			if(data.result){
				layer.alert("购买成功",{icon:1},function(index){
					layer.close(index);
					window.location.href="/user/personal?index=6"
				});
			}else{
				layer.msg(data.message);
			}
		});
	});
	
	
});