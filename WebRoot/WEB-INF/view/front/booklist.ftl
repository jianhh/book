	<@front.top "所有书籍-二手书"/>
	<link rel="stylesheet" type="text/css" href="/resource/front/css/booklist/core.css">
	<link rel="stylesheet" type="text/css" href="/resource/front/css/booklist/booklist.css">
	<div class="blank"></div>
	<div id="doc" style="background:#E9EAEB" class="page">
		<div id="hd" class="hd">

			<div class="drop-ax-container"></div>

		</div>
		<div id="bd" class="bd">

			<div class="bd-wrap">
				<div class="body">

					<div class="aside my-box-shadow">

						<div class="booklist_nav show-switch">
							<div class="hd">
								<a href="/book" class="list-title">全部图书分类<b
									class="ic-all" style="display: none;"></b></a>
							</div>
							<div class="bd"
								style="z-index: 101; position: relative; display: block;">
								<div id="all-category"  class="mod skin-category">
									<div class="inner">
										<#if list?if_exists>
										<#list list as type>
										<div class="cate-menu-box cate-menu-box-more" style="z-index: 1;">
											<div class="tab-cate">
												<div class="tab-cate-hd" style="background-color: rgb(245, 244, 242); border-color: rgb(237, 237, 237);">
													<a href="/book/list" style="color: rgb(51, 51, 51);">${type.booktypename!}</a> 
													<b class="ic ic-arrow" style="background-image: url(/resource/front/images/get_1457fb0.png); background-position: initial initial; background-repeat: no-repeat no-repeat;"></b>
												</div>
											</div>
											<div class="popup-cate" style="top: 0px; display: none; width: 590px; height: 142px; overflow: hidden;">
												<ul>
													<#if type.list?if_exists>
														<#list type.list as t>
															<li>
																<a title="${t.booktypename!}" href="javascript:;" data-id="${t.booktypeid!}">${t.booktypename!}</a>
															</li>
														</#list>
													</#if>
												</ul>
											</div>
										</div>
										</#list>
										</#if>
									</div>
								</div>
								<a href="?show=0" class="open" data-status="off"
									style="display: none;">收起<b class="ic-fold"></b></a>
							</div>

						</div>
						<div class="mod left_set" style="display: none;">
							<div class="current-category show-switch">
								<div class="first-level">
									<div class="tab-cate-hd1 tab-cate-first">
										<a href="/book/list/?show=0" class="first_level_select"></a>
									</div>
								</div>
							</div>
							<div class="cms_book">
								<a
									href="http://yuedu.baidu.com/promotion/activity/nafreelist?fr=fenlei"
									target="_blank"> <img alt="" title=""
									src="http://img.baidu.com/img/iknow/wenku/0yuanfenlei240X90.jpg"
									style="">
								</a> <a
									href="http://yuedu.baidu.com/promotion/activity/syzbs20151010?fr=fenlei"
									target="_blank"> <img alt="" title=""
									src="http://img.baidu.com/img/iknow/wenku/sbs240x90.jpg"
									style="">
								</a> <a
									href="http://yuedu.baidu.com/promotion/activity/mtsdwfz20151010?fr=fenlei"
									target="_blank"> <img alt="" title=""
									src="http://img.baidu.com/img/iknow/wenku/mt240x90.jpg"
									style="">
								</a>
							</div>
						</div>

					</div>


					<div class="main my-box-shadow" style="background:#fff">

						<div class="hd">

							<div id="page-curmbs" style="height:41px;line-height:41px;background: #34495e;color: #fff;" class="crumbs ui-crumbs show-switch">
								<ul alog-group="general.curmbs">
									<li style="padding-left:10px;color:#fff;" class="current logSend">全部图书(<font style="color:#ff4400"><span id="totalBook"></span></font>)</li>
								</ul>
							</div>

							<div class="sort show-switch" id="selectpx">
								<a href="javascript:;" class="sort-item sort-first sort-select ">
									<span class="">库存</span><b></b>
								</a> 
								<a href="javascript:;" class="sort-item ">
									<span class="">热度</span><b></b>
								</a>
								<a href="javascript:;" class="sort-item ">
									<span class="">最新</span><b></b>
								</a> 
								<a href="javascript:;" class="sort-item sort-price ">
									<span class="">价格</span><b class="price-up"></b><b class="price-down"></b>
								</a>
							</div>
						</div>
						<div class="bd" style="padding-left:30px">
							<div class="booklist">
								<div id="data_list" class="booklist-inner clearfix">
									 
								</div>
								<div id="data_temp" dataid data-num onmouseleave="hidebook(this)" onmouseenter="showbook(this)" class="book"
									style="display:none;background-color: rgb(253, 253, 251); background-position: initial initial; background-repeat: initial initial;">
									<input type="hidden" class="bookintro" data-content name/>		
									<a class="img-block al" data-id
										href="/book/detail/"
										target="_blank"> <img
										src="" class="defbook" data-path name>
									</a> <a class="al title-link"
										href="/book/detail/" data-id name
										target="_blank"><span class="title" data-name></span>
									</a>
									<p class="author_price">
										<span class="author" data-author></span> <span class="price"
											style="font-size: 12px;"> <span>库存:(</span><span class="bookprice" data-price></span>)
										</span>
									</p>
								</div>
								
							</div>
							<div id="pager" style="margin-bottom: 30px;">
								
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>
	<#-- 右侧栏 -->
	<div id="side-bar" style="/* right: 25px; */ display: block;" class="">
		<div class="btn-wrap mb5">
			<a href="###" class="side-bar-item gotop"><span class="ic"></span></a>
			<a href="" class="side-bar-item weibo bookpocket" target="_blank"><span class="ic"></span><span id="bookpocketnum" data-pocket-num="${bookpocketnum?default('0')}" class="text-tip">书袋(${bookpocketnum?default('0')})
			</span></a>
			<a href="###" class="side-bar-item weixin"> <span class="ic"></span>
				<span class="text-tip">关注<br>微信
			</span> <span class="tooltips-box side-bar-qr-wp" style="display: none;">
					<span class="abox"><span class="a1"></span><span class="a2"></span></span>
					<span class="side-bar-qr"></span> <span class="side-bar-qr-txt">扫描二维码，快速分享到微信朋友圈</span>
			</span>
			</a> 
		</div>
		<div class="btn-wrap">
			<a href="" class="side-bar-item fankui log-xsend"
				target="_blank"><span class="ic">意见反馈</span><span
				class="text-tip">意见反馈</span></a>
		</div>
		<div class="feedback-remind">
			<a href="###" class="feedback-remind-close"></a>
			<div class="feedback-remind-content"></div>
			<a href="http://tieba.baidu.com/p/3200067245" target="_blank"
				class="feedback-remind-link  log-xsend" data-logxsend="[2, 200206]"></a>
		</div>
	</div>

	<!-- 书本信息弹出窗 -->
	<div class="book_hover_card_wrap" style="display: none; left: 156px;">
		<div class="card_arrow">
		</div>
		<div class="book_hover_card">
			<div class="card_hd">
				<a href="/ebook/67c0c1fe27284b73f3425069?fr=booklist"
					title="" class="card_title" target="_blank"></a>
				<p class="card_author" ></p>
				<p class="card_desp"></p>
				<span class="card_price"></span>
				<div class="card_btn">
					<b class="cart_animate">+1</b>
					<a href="javascript:;" dataid style="display:none;" class="buybook mya mycart exclude"><b class="ic-cart"></b><span>加入书袋</span></a>
					<a href="javascript:;" dataid onclick="sellBook(this)" class="sellbook mya mycart exclude"><b class="ic-cart"></b><span>出售</span></a>
				</div>
			</div>
		</div>
		
		<!--  -->
		<form id="search_form">
			<input type="hidden" name="pageSize" value="15" />
			<input type="hidden" name="pageNumber" value="1" />
			<input type="hidden" name="orderBy" value=" BOOKNUM DESC "/>
		</form>
	</div>
	<script src="/resource/front/js/jquery.js"></script>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<script src="/resource/front/js/business/booklist.js"></script>
	<script>
		search();
	</script>
	<!-- 底部 -->
	<@front.bottom/>