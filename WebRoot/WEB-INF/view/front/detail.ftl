	<@front.top "书本详情页-二手书"/>
	<link rel="stylesheet" href="<@front.css/>/bookdetail/global.min.css">
	<link rel="stylesheet"	href="<@front.css/>/bookdetail/jquery.spinner.css">
	<link rel="stylesheet" href="<@front.css/>/bookdetail/index.css">
	<style>
		.bd-wrap img{
			width:120px;
			height:150px;
		}
	</style>
	<div id="page">
		<div class="cdj-container cdj-detail-wrap">
			<div class="cdj-row">
				<div class="cdj-col-18 detail-container">
					<input type="hidden" name="bookid" value="${obj.bookid}" />
					<!-- 信息模块（正常状态） -->
					<div class="my-box-shadow detail-summary" id="J_Summary">

						<div class="detail-images" id="J_ImagesSlide">
							<div class="detail-main-image">
								<div class="tab-content">
									<div class="tab-pannel" style="display: block;">
										<img style="width:200px;height:300px;" src="${obj.bookpicpath!}">
									</div>
								</div>
							</div>

						</div>
						<div class="detail-info-wrap">
							<div class="detail-info">
								<div class="d-title cdj-font">${obj.bookname!}</div>
								<div class="d-desc">
									<@tools.subContents obj.bookintro?default("暂无简介") 130/>
								</div>
								<div class="d-line">
									<div class="d-date">上传时间：<@tools.subDate obj.bookaddtime 10/></div>
								</div>
								<div class="d-price">
									原　　价：￥<em>${obj.bookoldprice!}</em>元 　　
								</div>
								<div class="d-keywords" style="display: inline-flex;">
									数　　量： <input type="text" class="spinner" /> 本 (库存${obj.booknum!}本)
								</div>
								<br />
								<div class="d-more-info"></div>

							</div>
						</div>
						<#if obj.booknum !='0'>
							<div class="d-action">
								<span dataid="${obj.bookid!}" class="collectbook J_Like cdj-item-like">
									<div class="tbc-like-wrap">
										<#if collect?if_exists>取消收藏<#else>收藏</#if>
									</div>
								</span>
								<span dataid="${obj.bookid!}" class="sellbook J_Like cdj-item-like">
									<div class="tbc-like-wrap">
										出售
									</div>
								</span>
								<span class="J_Like cdj-item-like">
									<div class="tbc-like-wrap">
										<a href="###" id="doc-add-cart" class="add-to-cart"> <span
											class="ic ic-cart" id="ca"></span> <span class="txt"
											style="font-size:14px;color:#f97a28;">加入购物车</span> <span
											class="doc-operate-tip doc-operate-tip-cart"></span>
										</a>
									</div>
								</span>
								
							</div>
						<#else>
							<div class="d-action">
								<span dataid="${obj.bookid!}" class="collectbook J_Like cdj-item-like">
									<div class="tbc-like-wrap">
										<#if collect?if_exists>取消收藏<#else>收藏</#if>
									</div>
								</span>
								<span dataid="${obj.bookid!}" class="sellbook J_Like cdj-item-like">
									<div class="tbc-like-wrap">
										出售
									</div>
								</span>
							</div>
						</#if>
						
						<div class="alarm-tip">
							<span class="lx-close"></span>
						</div>
					</div>
					<div class="blank"></div>
					<!-- 商家 -->
					<div class="my-box-shadow cdj-top-seller">
						<div class="top">
						    <div class="store">
								<a class="name" href="" target="_blank">商家出售</a>
								
								<span class="more-link J_MoreSeller"></span>
							</div>
						    <div class="detail">
								<div class="item">
									<table class="table table-bordered table-hover table-striped" id="sell-table">
								        <thead>
								          <tr>
								         	<th>学校</th>
								            <th>学院</th>
								            <th>用户名</th>
								            <th>数量(本)</th>
								            <th>售价(元)</th>
								            <th>卖家备注</th>
								            <th>操作</th>
								          </tr>
								        </thead>
								        
								        <tbody id="seller_temp" style="display:none;">
								          <tr>
								          	<td>广东药学院</td>
								            <td>信息工程学院</td>
								            <td>何小灰</td>
								            <td>1</td>
								            <td>￥26</td>
								            <td><a href="javascript:void(0)" data-remark="" class="look-remark" title="">查看</a></td>
								            <td><a href="javascript:void(0)" data-seller="" class="buybtn" title=""> 
								            	<span class="ic ic-cart" id="ca"></span></a>
								            </td>
								          </tr> 
								        </tbody>
								        
								      </table>
								</div>
						    </div>
						</div>
						<!-- 更多 -->
						<div onclick="searchSeller()" class="moreseller-btn">
							<input type="hidden" id="sellerPageNum" value="1" />
							<span>查看更多卖家</span>
						</div>
					</div>	
					<!-- 商家end -->
					
					<div class="my-box-shadow detail-content" style="margin-top:10px;" id="J_DetailSlide">
						<div class="J_DetailTabNav tab-nav-wrap nav-fixed">
							<ul class="detail-tab-nav cdj-font">

								<li class="current">基本信息</li>

								<li>评论 <em class="cdj-color-2">200</em>
								</li>

							</ul>
							<div class="nav-fixed-right J_NavFixRight" style="display: none;"
								data-spm="ambh1"></div>
						</div>
						<div class="detail-tab-content" style="padding-top: 0px;">

							<div data-spm="1998931292"
								class="detail-tab-pannel detail-pannel"
								style="display: block; overflow: hidden;">

								<div class="mod book-des book-intro-block" id="book-des">

									<a name="desp" class="anchor anchor1" id="desp">&nbsp;</a>
									<div class="new-title">
										<span class="title-icon"></span> <span class="title-txt">图书简介</span>
									</div>
									<div class="bd scaling-content-wp">
										<div class="scaling-content" style="height:130px;">
											<p>
											${obj.bookintro?default('暂无简介')}
											</p>
										</div>
										<p class="scaling-more-btn" style="display: block;">
											<a href="###" class="expand"><span class="text">查看全部</span><span
												class="ic"></span></a>
										</p>
									</div>
								</div>

								<div class="mod book-intro-block mb20" style="margin-top:20px;">

									<div class="new-title book-info-title">
										<span class="title-icon"></span> <span class="title-txt">基本信息</span>
									</div>
									<div class="bd">
										<ul class="book-information clearfix">
											<li><span class="book-information-tip">作 <span
													class="ml28">者：</span></span> ${obj.bookauthor!}</li>
											<li><span class="book-information-tip">出版时间：</span>
												<@tools.subDate obj.bookpublishtime 10/></li>
											<li><span class="book-information-tip">出<span
													class="ml7">版</span><span class="ml7">社：</span></span> ${obj.bookpublish!}</li>
											<li><span class="book-information-tip">纸书定价：</span>
												${obj.bookoldprice!}元</li>
											<li class="book-information-classic "><span
												class="classic-type book-information-tip">分 <span
													class="ml28">类：</span></span><span>${obj.booktypename!} &gt; ${obj.booktypesonname!}</span></li>
										</ul>
									</div>

								</div>

								<div class="mod book-intro-block mb20" style="margin-top:20px;">

									<div class="new-title book-info-title" id="pl">
										<span class="title-icon"></span> <span class="title-txt">评论</span>
									</div>
									<div class="bd">
										<ul class="comment-list" id="data_list">

										</ul>
										<li id="data_temp" style="display:none;" class="comment-list-item ">
											<div class="comment-pic-wrap">
												<div class="img-down-wp">
													<img class="img-down"
														src="" data-pic>
													<span class="author-circle"></span>
												</div>
											</div>
											<div class="comment-info clearfix no-margin-top">
												<span class="comment-author" data-author></span>
												<span style="float:right" class="comment-date" data-time></span> 
												<span class="comment-score"><b class="ic ic-star-m-on"></b><b
													class="ic ic-star-m-on"></b><b class="ic ic-star-m-on"></b><b
													class="ic ic-star-m-on"></b><b class="ic ic-star-m-on"></b></span>
											</div>
											<p class="comment-short" data-con></p>
										</li>
										
										<#--分页 -->
										<div id="pager" style="margin-top: 30px;text-align: center;">
											
										</div>
													
									</div>
									
								</div>


								<!--评论 -->
								<div class="bd" style="margint-top:60px">
									<div class="uncomment" style="display: -webkit-box;">
										<div class="user-icon-wp" style="display: block;padding: 0 22px 0 0px;margin-bottom: 10px;">
											<div class="user-icon">
												<div class="user-icon-mask"></div>
												<img
													src="http://himg.bdimg.com/sys/portraitn/item/f445b7e3c7e5d4c6c2e4642f"
													alt="" class="user-icon-pic"> <span
													class="author-circle"></span>
											</div>
										</div>
										<div class="comment-sub " >
											<div class="clearfix comment-sub-wp" style="display: flex;margin-top: 10px;">
												<span style="font-size: 14px;">正文</span>
												<textarea class="comment-content-sub "
													placeholder="必填，不少于5个字"></textarea>
											</div>
											<div class="comment-vcode-wrap clearfix" style="margin-top:10px;">
												<span class="comment-vcode-label" style="font-size: 14px;">验证码</span> <input
													type="text" id="checkcode" class="comment-vcode-input" maxlength="4">
												<img style="height:30px;" style="cursor:pointer" id="kaptchaImage" alt="点击更换"
								title="点击更换" src="/Kaptcha.jpg" /><span style="cursor:pointer" 
													class="comment-vcode-change changeVerifyCode">看不清，换一张</span>
											</div>
											<div class="comment-vcode-wrap clearfix" style="margin-top:10px;">
												<div class="fbpl"><b>发表评论</b></div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<!-- =================================================== 这是改动内容 ====================================================== -->

				<div class="cdj-col-6">

					<div class="my-box-shadow normal-mod detail-container">
						<div class="hd cdj-font">你可能感兴趣的</div>
						<div class="bd">
							<#list li1 as b>
								<div class="bd-wrap">
									<div class="bd-image">
										<a href="/book/detail/${b.bookid}" target="_blank"> <img
											src="${b.bookpicpath}">
										</a>
									</div>
									<div class="bd-info">
										<div class="bd-normal-title" style="margin-top:10px;">
											<a href="/book/detail/${b.bookid}"
												target="_blank" title="${b.bookname}"><em>${b.bookname}</em></a>
										</div>
	
										<div class="bd-normal-price" style="margin-top:10px;">
											<em>${b.bookauthor}</em>
										</div>
										<div class="bd-normal-price" style="margin-top:10px;">
											￥<em>${b.bookoldprice}</em>元
										</div>
										<div class="bd-normal-price" style="margin-top:10px;">
											<em><@tools.subDate b.bookaddtime 10/></em>
										</div>
									</div>
								</div>
							</#list>
						</div>
					</div>

					<div class="my-box-shadow normal-mod detail-container">
						<div class="hd cdj-font">大家都喜欢</div>
						<div class="bd">
							<#list li2 as b>
								<div class="bd-wrap">
									<div class="bd-image">
										<a href="/book/detail/${b.bookid}" target="_blank"> <img
											src="${b.bookpicpath}">
										</a>
									</div>
									<div class="bd-info">
										<div class="bd-normal-title" style="margin-top:10px;">
											<a href="/book/detail/${b.bookid}"
												target="_blank" title="${b.bookname}"><em>${b.bookname}</em></a>
										</div>
	
										<div class="bd-normal-price" style="margin-top:10px;">
											<em>${b.bookauthor}</em>
										</div>
										<div class="bd-normal-price" style="margin-top:10px;">
											￥<em>${b.bookoldprice}</em>元
										</div>
										<div class="bd-normal-price" style="margin-top:10px;">
											<em><@tools.subDate b.bookaddtime 10/></em>
										</div>
									</div>
								</div>
							</#list>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form id="searchForm">
		<input type="hidden" name="orderBy" value=" COMMENTTIME desc " />
		<input type="hidden" name="ppNames" value="COMMENTBOOKID#=" />
		<input type="hidden" name="keywords" value="${obj.bookid}" />
	</form>
	<script type="text/javascript" src="<@front.js/>/jquery.js"></script>
	<script type="text/javascript" src="<@front.js/>/jquery.spinner.js"></script>
	<script type="text/javascript" src="/resource/common/js/layer/layer.js"></script>
	<script type="text/javascript" src="/resource/common/js/laypage/laypage.js"></script>
	<script src="/resource/common/js/z/zUtils.js"></script>
	<script src="/resource/common/js/z/zPager.js"></script>
	<script type="text/javascript" src="<@front.js/>/business/bookdetail.js"></script>
	<script type="text/javascript">
		search();
		searchSeller();
		$(function() {
			$(".J_DetailTabNav").removeClass("nav-fixed");
			var nav = $("#J_DetailSlide"); //得到导航对象
			var win = $(window); //得到窗口对象
			var sc = $(document);//得到document文档对象。
			win.scroll(function() {
				if (sc.scrollTop() === 0) {
					return;
				}
				if (sc.scrollTop() - nav.get(0).offsetTop >= 0) {
					$(".J_DetailTabNav").addClass("nav-fixed");
				} else {
					$(".J_DetailTabNav").removeClass("nav-fixed");
				}
			});
			$(".expand").click(function() {
				if ($(".scaling-content").height() == 130) {
					$(this).find(".text").text("收起");
					$(".scaling-content").animate({
						"height" : $(".scaling-content p").height() + 10 + "px"
					}, 200);
				} else {
					$(this).find(".text").text("查看全部");
					$(".scaling-content").animate({
						"height" : "130px"
					}, 200);
				}
			});

		});
		$('.spinner').spinner();

		$(".detail-tab-nav li").click(function() {
			if ($(this).index() === 0) {
				$("html,body").animate({
					scrollTop : $("#J_DetailSlide").offset().top - 15
				}, 800);
			} else {
				$("html,body").animate({
					scrollTop : $("#pl").offset().top - 60
				}, 800);
			}

		});
	</script>
	<script>
		$(".user_circular").error(function(){
		  $(this).attr("src","/resource/common/images/defuser.png");
		});
	</script>
	</div>
	</div>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<@front.rightBox/>
	<!-- 底部 -->
	<@front.bottom/>