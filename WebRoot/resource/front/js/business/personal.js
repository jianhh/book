$("#updateemail").click(function(){
	var op = layer.open({
		type : 1,
		closeBtn : 1,
		shadeClose : true, //开启遮罩关闭
		content : $('.msg-layer')
	});
});

$(".btn-close").click(function() {
	layer.closeAll();
});


$(".wrap-boxes li").click(function() {
	showright($(this).index());
});

function subcon(str,length){
	if(str==null || str=="" || typeof(str)=='undefined'){
		return "暂无";
	}
	if(str.length<=length){
		return str;
	}
	return str.substring(0,length)+"...";
	
}
//收藏分页
function search(pageNum) {
	
	if(pageNum!="" && typeof(pageNum)!='undefined'){
		$("input[name='pageNumber']").val(pageNum);
	}
    $.post(BASE.basePath + "/user/getCollectPager",$('#search_form').serialize(),function(data){
    	//
    	if(!data.result){
    		layer.msg("没有数据");
    		return;
    	}
    	$(".totalBook").text(data.pager.totalCount);
    	//填充数据
    	var datalist = $("#data_list");
    	datalist.empty();
    	
    	for(var i=0;i<data.pager.list.length;i++){
    		var temp = $("#data_temp").clone(true);
    		if(i==0){
    			temp.addClass("tl-item-first");
    		}
    		var tempul = temp.find("ul");
    		
    		var list = data.pager.list[i];
    		for(var j=0;j<list.length;j++){
    			var b = list[j];
    			var templi = $("#data_temp_li").clone(true);
    			templi.attr("id","book_"+b.bookid);
        		templi.find("[data-id]").attr("href",templi.find("[data-id]").attr("href")+b.bookid);
        		templi.find("[data-intro]").html($.trim(subcon(b.bookintro,90)));
        		templi.find("[data-author]").text(b.bookauthor);
        		templi.find("[data-price]").text(b.bookoldprice+"元");
        		templi.find("[data-name]").text(b.bookname);
        		templi.find("[data-img]").attr("src",b.bookpicpath);
        		templi.find("[data-publish]").text(b.bookpublish);
        		templi.find("[data-time]").text(b.bookpublishtime);
        		templi.find("[dataid]").attr("dataid",b.bookid);
        		//设置收藏日期
        		if(j==0){
        			temp.find("[data-year]").text(b.bookaddtime.substring(0,4));
        			temp.find("[data-other-date]").text(b.bookaddtime.substring(5));
        		}
        		/*if(b.booknum<1){
        			templi.find(".buybook").css("display","none");
        			templi.find(".sellbook").css("display","block");
        		}else{
        			templi.find(".buybook").css("display","block");
        			templi.find(".sellbook").css("display","none");
        		}*/
        		templi.css("display","");
        		tempul.append(templi);
    		}
    		temp.attr("id","");
    		temp.css("display","");
    		datalist.append(temp);
    		
    		
    	}
    	$("#data_list li").eq($("#data_list li").size()-1).addClass("tl-item-last");;
    	
    	laypage({
	        cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
	        skin: '#53b856',
	        groups: 5 ,//连续显示分页数
            pages: data.pager.pageCount, //通过后台拿到的总页数
            curr: pageNum || 1, //当前页
            jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                    search(obj.curr);
                    $('html, body').animate({scrollTop:0}, 'slow');
                }
            }
	    });
    });
}
//正在寄售分页
function sellsearch1(pageNum) {
	
	if(pageNum=="" || typeof(pageNum)=='undefined'){
		pageNum = "1";
	}
	$.post(BASE.basePath + "/user/getSellPager",{pageNumber:pageNum,pageSize:6,status:"0"},function(data){
		//
		if(!data.result){
			layer.msg("没有数据");
			return;
		}
		$(".selltotalBook").text(data.pager.totalCount);
		//填充数据
		var datalist = $("#sell_list");
		datalist.empty();
		
		for(var i=0;i<data.pager.list.length;i++){
			var temp = $("#sell_temp").clone(true);
			if(i==0){
				temp.addClass("tl-item-first");
			}
			var tempul = temp.find("ul");
			
			var list = data.pager.list[i];
			for(var j=0;j<list.length;j++){
				var b = list[j];
				var templi = $("#sell_temp_li").clone(true);
				templi.attr("id","book_"+b.sellid);
				templi.find("[data-id]").attr("href",templi.find("[data-id]").attr("href")+b.bookVo.bookid);
				templi.find("[data-intro]").html($.trim(subcon(b.bookVo.bookintro,90)));
				templi.find("[data-author]").text(b.bookVo.bookauthor);
				templi.find("[data-price]").text(b.bookVo.bookoldprice+"元");
				templi.find("[data-nowprice]").text(b.price+"元 ("+b.discount+"折)");
				templi.find("[data-name]").text(b.bookVo.bookname);
				templi.find("[data-img]").attr("src",b.bookVo.bookpicpath);
				templi.find("[data-publish]").text(b.bookVo.bookpublish);
				templi.find("[data-time]").text(b.bookVo.bookpublishtime);
				templi.find("[data-sellid]").attr("data-sellid",b.sellid);
				templi.find("[data-num]").text(b.num);
				templi.find("[data-sellnum]").text(b.num-b.sellnum);
				//设置收藏日期
				if(j==0){
					temp.find("[data-year]").text(b.selltime.substring(0,4));
					temp.find("[data-other-date]").text(b.selltime.substring(5));
				}
				/*if(b.booknum<1){
        			templi.find(".buybook").css("display","none");
        			templi.find(".sellbook").css("display","block");
        		}else{
        			templi.find(".buybook").css("display","block");
        			templi.find(".sellbook").css("display","none");
        		}*/
				if(b.sellStatus=='0'){
					templi.find("[data-status]").text("出售状态");
					templi.find(".study-points .status1").css("display","block");
				}else{
					templi.find("[data-status]").text("下架状态");
					templi.find(".study-points .status0").css("display","block");
				}
				templi.css("display","");
				tempul.append(templi);
			}
			temp.attr("id","");
			temp.css("display","");
			datalist.append(temp);
			
			
		}
		$("#sell_list li").eq($("#data_list li").size()-1).addClass("tl-item-last");;
		
		laypage({
			cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
			skin: '#53b856',
			groups: 5 ,//连续显示分页数
			pages: data.pager.pageCount, //通过后台拿到的总页数
			curr: pageNum || 1, //当前页
			jump: function(obj, first){ //触发分页后的回调
				if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
					search(obj.curr);
					$('html, body').animate({scrollTop:0}, 'slow');
				}
			}
		});
	});
}
//出售订单分页
function sellsearch(pageNum) {
	
	if(pageNum=="" || typeof(pageNum)=='undefined'){
		pageNum = "1";
	}
	$.post(BASE.basePath + "/user/getSellPager",{pageNumber:pageNum,pageSize:6,status:"1"},function(data){
		//
		if(!data.result){
			layer.msg("没有数据");
			return;
		}
		$(".ordertotalBook").text(data.pager.totalCount);
		//填充数据
		var datalist = $("#order_list");
		datalist.empty();
		
		for(var i=0;i<data.pager.list.length;i++){
			var temp = $("#order_temp").clone(true);
			if(i==0){
				temp.addClass("tl-item-first");
			}
			var tempul = temp.find("ul");
			
			var list = data.pager.list[i];
			for(var j=0;j<list.length;j++){
				var b = list[j];
				var templi = $("#order_temp_li").clone(true);
				templi.attr("id","book_"+b.bookVo.bookid);
				templi.find("[data-id]").attr("href",templi.find("[data-id]").attr("href")+b.bookVo.bookid);
				templi.find("[data-intro]").text(b.bookAddressVo.pname+" "+b.bookAddressVo.cname+" "+b.bookAddressVo.coname+" "+b.bookAddressVo.detailaddress+"， 邮编："+b.bookAddressVo.postalcode+"， 收货人："+b.bookAddressVo.consignee+"， 联系电话："+b.bookAddressVo.phonenumber+"(留言："+b.buyremark+")");
				templi.find("[data-author]").text(b.bookVo.bookauthor);
				templi.find("[data-price]").text(b.bookVo.bookoldprice+"元");
				templi.find("[data-nowprice]").text(b.bookSellVo.price+"元 ("+b.bookSellVo.discount+"折)");
				templi.find("[data-name]").text(b.bookVo.bookname);
				templi.find("[data-img]").attr("src",b.bookVo.bookpicpath);
				templi.find("[data-publish]").text(b.bookVo.bookpublish);
				templi.find("[data-time]").text(b.bookVo.bookpublishtime);
				templi.find("[data-buyid]").attr("data-buyid",b.buyid);
				templi.find("[data-user]").text(b.bookUserVo.username);
				templi.find("[data-totalprice]").text(b.buytotalamount+"元");
				templi.find("[data-buynum]").text(b.buynumber+"本");
				templi.find("[data-user]").attr("info","学校："+b.bookUserVo.schoolname+b.bookUserVo.collegename+"<br/> 短号："+b.bookUserVo.shortphonenumber+"<br/>邮箱："+b.bookUserVo.email);
				//设置收藏日期
				if(j==0){
					temp.find("[data-year]").text(b.buytime.substring(0,4));
					temp.find("[data-other-date]").text(b.buytime.substring(5));
				}
				/*if(b.booknum<1){
        			templi.find(".buybook").css("display","none");
        			templi.find(".sellbook").css("display","block");
        		}else{
        			templi.find(".buybook").css("display","block");
        			templi.find(".sellbook").css("display","none");
        		}*/
				if(b.buystatu=='1'){
					templi.find("[data-status]").text("未发货");
				}else if(b.buystatu=='2'){
					templi.find("[data-status]").text("已发货");
					templi.find(".study-points a").css("display","none");
				}else{
					templi.find("[data-status]").text("交易成功");
					templi.find(".study-points a").css("display","none");
				}
				templi.css("display","");
				tempul.append(templi);
			}
			temp.attr("id","");
			temp.css("display","");
			datalist.append(temp);
			
			
		}
		$("#sell_list li").eq($("#data_list li").size()-1).addClass("tl-item-last");;
		
		laypage({
			cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
			skin: '#53b856',
			groups: 5 ,//连续显示分页数
			pages: data.pager.pageCount, //通过后台拿到的总页数
			curr: pageNum || 1, //当前页
			jump: function(obj, first){ //触发分页后的回调
				if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
					search(obj.curr);
					$('html, body').animate({scrollTop:0}, 'slow');
				}
			}
		});
	});
}
//购买分页
function buysearch(pageNum,status) {
	
	if(pageNum!="" && typeof(pageNum)!='undefined'){
		$("input[name='pageNumber']").val(pageNum);
	}
	if(status="" && typeof(status)=='undefined'){
		status = "all";
	}
	$.post(BASE.basePath + "/user/getBuyPager",{pageNumber:pageNum,pageSize:6,status:status},function(data){
		//
		if(!data.result){
			layer.msg("没有数据");
			return;
		}
		$(".buytotalBook").text(data.pager.totalCount);
		//填充数据
		var datalist = $("#buy_list");
		datalist.empty();
		
		for(var i=0;i<data.pager.list.length;i++){
			var temp = $("#buy_temp").clone(true);
			if(i==0){
				temp.addClass("tl-item-first");
			}
			var tempul = temp.find("ul");
			
			var list = data.pager.list[i];
			for(var j=0;j<list.length;j++){
				var b = list[j];
				var templi = $("#buy_temp_li").clone(true);
				templi.attr("id","book_"+b.bookVo.bookid);
				templi.find("[data-id]").attr("href",templi.find("[data-id]").attr("href")+b.bookVo.bookid);
				templi.find("[data-buyid]").attr("href",templi.find("[data-buyid]").attr("href")+b.buyid);
				templi.find("[data-intro]").html($.trim(subcon(b.bookVo.bookintro,90)));
				templi.find("[data-author]").text(b.bookVo.bookauthor);
				templi.find("[data-price]").text(b.bookVo.bookoldprice+"元");
				templi.find("[data-name]").text(b.bookVo.bookname);
				templi.find("[data-img]").attr("src",b.bookVo.bookpicpath);
				templi.find("[data-publish]").text(b.bookVo.bookpublish);
				templi.find("[data-time]").text(b.bookVo.bookpublishtime);
				templi.find("[dataid]").attr("dataid",b.bookVo.bookid);
				templi.find("[databuyid]").attr("databuyid",b.buyid);
				templi.find("[data-nowprice]").text(b.bookSellVo.price+"元 ("+b.bookSellVo.discount+"折)");
				//设置收藏日期
				if(j==0){
					templi.find("[data-year]").text(b.buytime.substring(0,4));
					templi.find("[data-other-date]").text(b.buytime.substring(5));
				}
				if(b.buystatu=='0'){
					templi.find("[data-status]").text("未付款");
					templi.find(".study-points .status0").css("display","block");
				}else if(b.buystatu=='1' || b.buystatu=='2'){
					if(b.buystatu=='1'){
						templi.find("[data-status]").text("未发货");
					}else{
						templi.find("[data-status]").text("已发货");
					}
					
					templi.find(".study-points .status0").css("display","none");
				}else{
					templi.find("[data-status]").text("交易成功");
					templi.find(".study-points a").css("display","none");
				}
				/*if(b.booknum<1){
        			templi.find(".buybook").css("display","none");
        			templi.find(".sellbook").css("display","block");
        		}else{
        			templi.find(".buybook").css("display","block");
        			templi.find(".sellbook").css("display","none");
        		}*/
				templi.css("display","");
				tempul.append(templi);
			}
			temp.attr("id","");
			temp.css("display","");
			datalist.append(temp);
			
			
		}
		$("#buy_list li").eq($("#data_list li").size()-1).addClass("tl-item-last");;
		
		laypage({
			cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
			skin: '#53b856',
			groups: 5 ,//连续显示分页数
			pages: data.pager.pageCount, //通过后台拿到的总页数
			curr: pageNum || 1, //当前页
			jump: function(obj, first){ //触发分页后的回调
				if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
					search(obj.curr);
					$('html, body').animate({scrollTop:0}, 'slow');
				}
			}
		});
	});
}

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
function showright(index){
	$(".wrap-boxes li").removeClass("nav-active");
	$(".wrap-boxes li").eq(index).addClass("nav-active");
	
	$(".setting-right").css("display","none");
	$(".setting-right").eq(index).css("display","block");
	if(index==4){
		search();
	}else if(index==6){
		buysearch();
	}else if(index==7){
		sellsearch();
	}else if(index==8){
		sellsearch1();
	}
}
var index = $("#data-index").val();
if(index>$(".wrap-boxes li").size()){
	index = 0;
}
showright(index)

function selectCollege(obj){
	var schoolid = $(obj).val();
	$.post('/userBack/doListByFatherId',{fatherId:schoolid},function(rm){
		if(rm.result){
			$("#college-select").empty();
			var html = '<option value="">学院</option>';
			$.each(rm.list,function(i,o){
				html = html + '<option value="'+this.dicid+'">'+this.dicname+'</option>';
			})
			$("#college-select").append(html);
		}
	});
}

//购买记录-分类
$(".buyrecord").click(function(){
	if($(".buyrecord").hasClass("icon-drop_down")){
		$(".buyrecord").removeClass("icon-drop_down");
		$(".buyrecord").addClass("icon-drop_up");
		$("#js-columbd").css("display","block");
	}else{
		$(".buyrecord").removeClass("icon-drop_up");
		$(".buyrecord").addClass("icon-drop_down");
		$("#js-columbd").css("display","none");
	}
});

//出售记录-分类
$(".sellrecord").click(function(){
	if($(".sellrecord").hasClass("icon-drop_down")){
		$(".sellrecord").removeClass("icon-drop_down");
		$(".sellrecord").addClass("icon-drop_up");
		$("#js-columcs").css("display","block");
	}else{
		$(".sellrecord").removeClass("icon-drop_up");
		$(".sellrecord").addClass("icon-drop_down");
		$("#js-columcs").css("display","none");
	}
});

//确认订单
$(".confirmbook").click(function(){
	var databuyid = $(this).attr("databuyid");
	var p = $(this);
	$.post("/user/confirmOrder",{id:databuyid},function(data){
		if(data.result){
			layer.msg("提交成功");
			p.parent().find("a").css("display","none");
			p.prev().text("交易成功");
		}else{
			layer.msg(data.message);
		}
	});
});

//取消订单
$(".cancelbook").click(function(){
	var databuyid = $(this).attr("databuyid");
	var p = $(this);
	$.post("/user/cancelOrder",{id:databuyid},function(data){
		if(data.result){
			layer.msg("取消订单成功");
			p.parent().parent().parent().remove();
		}else{
			layer.msg(data.message);
		}
	});
});
//下架书籍
$(".outbook").click(function(){
	var sellid = $(this).attr("data-sellid");
	var p = $(this);
	$.post("/book/outbook",{id:sellid},function(data){
		if(data.result){
			layer.msg("下架成功");
			p.parent().find("[data-status]").text("下架状态");
			p.prev().css("display","block");
			p.css("display","none");
		}else{
			layer.msg("提交参数错误，请刷新页面再试");
		}
	});
});
//上架书籍
$(".putbook").click(function(){
	var sellid = $(this).attr("data-sellid");
	var p = $(this);
	$.post("/book/putbook",{id:sellid},function(data){
		if(data.result){
			layer.msg("上架成功");
			p.next().css("display","block");
			p.css("display","none");
			p.parent().find("[data-status]").text("出售状态");
		}else{
			layer.msg("提交参数错误，请刷新页面再试");
		}
	});
});

//修改库存
$(".setbook").click(function(){
	var sellid = $(this).attr("data-sellid");
	var p = $(this);
	layer.prompt({
	  title: '输入该书籍现在库存数',
	  formType: 0 //prompt风格，支持0-2
	}, function(num){
		$.post("/book/setbook",{id:sellid,num:num},function(data){
			if(data.result){
				layer.msg("操作成功");
				parent.location.href="/user/personal?index=8";
			}else{
				layer.msg(data.message);
			}
		});
	});
});
//修改成已发货
$(".updatebuystatus").click(function(){
	var buyid = $(this).attr("data-buyid");
	var p = $(this);
	$.post("/book/setbuystatus",{id:buyid},function(data){
		if(data.result){
			layer.msg("修改状态成功");
			p.parent().find("[data-status]").text("已发货");
			p.addClass("hide");
		}else{
			layer.msg("提交参数错误，请刷新页面再试");
		}
	});
});
//显示用户信息
$(".userinfo").click(function(){
	var info = $(this).attr("info");
	if(info!=""){
		layer.tips(info, $(this), {
			  tips: [2, '#53b856']
			});
	}
});

$(".savePwd").click(function(){
	
	$.post("/user/resetpwd",$("#resetpwdform").serialize(),function(data){
		if(data.result){
			layer.msg("修改成功");
		}else{
			layer.msg(data.message);
		}
	});
	
});


//基于百度WebUpload上传
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
	 $("#userpic").attr("src",b.o.uploadfilepath);
     $.post("/user/changepic",{pic:b.o.uploadid},function(data){
    	 if(data.result){
    		 $(".userheadpic").attr(src,b.o.uploadfilepath);
    	 }else{
    		 layer.msg(data.message);
    	 }
     });
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
  layer.msg(msg);
}

$('#js-face-reault').on('click', '.rm-face-reault', function() {
  $('#js-face-reault').empty();
  $('#art-face').val('');
  uploader.reset();
});
}();