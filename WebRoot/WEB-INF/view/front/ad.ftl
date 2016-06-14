	<@front.top "公告-二手书"/>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<style>
		.layout:after, .main-wrap:after, .col-sub:after, .col-extra:after, .tb-module:after, .clearfix:after {
content: '.';
display: block;
height: 0;
line-height: 0;
clear: both;
visibility: hidden;
}
	</style>
	
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-title cdj-font tb-lazyload"
				style="background:none" id="cdj_floor3">
				<p class="cdj-title">最新公告</p>
				<p class="cdj-desc">本站公告，领略最新资讯</p>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-trend-special-ver2 tb-lazyload">
				<div class="cdj-container sort-wrap">
					<ul id="dataList" class="sort-container cdj-font">
							
					</ul>
					<li id="template" style="display:none;" class="item size1">
						<a href="/index/adetail/" data-id target="_blank"> 
							<img src="">
							<span class="title"></span> 
							<span class="ext">
								<i class="icon-clock"></i>
								<span class="date"></span>
							</span>
							<span class="desc"></span>
						</a>
					</li>
				</div>
			</div>
		</div>
		<div id="pager" style="text-align: center;"></div>
	</div>
	<script>
			
	function search(curr) {
		
	    $.post("/index/getAdPager",{pageNumber:curr},function(data){
	    	if(!data.result){
	    		layer.msg("没有数据");
	    		return;
	    	}
	    	var list = $("#dataList");
	    	list.empty();
	    	$.each(data.pager.list,function(i,b){
	    		var temp = $("#template").clone(true);
	    		temp.attr("id","ad_"+b.articleid);
	    		temp.find("[data-id]").attr("href",temp.find("[data-id]").attr("href")+b.articleid);
	    		temp.find(".title").text(b.articletitle);
	    		temp.find(".date").text(b.articletime);
	    		
	    		var str = "暂无简介";
	    		
	    		if(b.articlecontent!="" && b.articlecontent.length>35){
	    			str = b.articlecontent.substring(0,35)+"...";
	    		}else if(b.articlecontent.length<35){
	    			str = b.articlecontent;
	    		}
	    		
	    		temp.find(".desc").html(str);
	    		temp.find("img").attr("src",b.picpath);
	    		
	    		temp.css("display","");
	    		list.append(temp);
	    	});
	    	
	    	laypage({
		        cont: $('#pager'), //容器。值支持id名、原生dom对象，jquery对象,
		        skip: true, //是否开启跳页
		        skin: '#53b856',
		        groups: 5 ,//连续显示分页数
	            pages: data.pager.pageCount, //通过后台拿到的总页数
	            curr: curr || 1, //当前页
	            jump: function(obj, first){ //触发分页后的回调
	                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                    search(obj.curr);
	                    $("html,body").animate({
	    					scrollTop : $("body").offset().top - 60
	    				}, 300);
	                }
	            }
		    });
	    });
	}
	search(1);
	</script>
	<!-- 底部 -->
	<@front.bottom/>