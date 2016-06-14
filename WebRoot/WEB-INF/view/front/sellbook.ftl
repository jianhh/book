<@front.top "出售书籍-二手书"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="/resource/front/css/pay/cart_page.css" />
<link rel="stylesheet" href="/resource/front/css/search.css" />
<style>
#cart-page-wrap .cart-bd .books-group .book-enable .price-wrap .book-price{color:#48B94C;}
</style>

	<div id="main" style="display:block;background: url(/resource/front/images/sbg2.jpg);">
		<div class="search-container">
			<!--banner start-->
			<div class="search-banner">
				<div class="search-box clearfix" data-search="search-page">
					<div class="suggest-input-box l">
						<input id="search_key" class="suggest-input J-suggest-input" placeholder="请输入想搜索的书籍信息..." type="text" value=""> <s class="btn-text-clear" id="clear-content" title="清空">清空</s>
					</div>
					<input type="button" class="btn-search" onclick="search()" id="search_btn" value="搜索">
				</div>
			</div>
		</div>
	</div>
	<div id="bd" style="background:#E9EAEB;" class="bd">

		<div class="bd-wrap" style="background:#fff;width:1200px!important;">
			<div class="body my-box-shadow" style="margin-top:40px;">

				<div id="cart-page-wrap" style="padding:0 20px;" class="cart-page">
					<div class="cart-hd">
						<div style="border-bottom:2px solid #EEA185;" class="cart-table-hd clearfix">
							<span class="th-item item-info">书籍信息<span class="total"></span></span> 
							<span style="left:325px;" class="th-item item-info">ISBN码</span> 
							<span style="left:525px;" class="th-item item-info">出版社</span> 
							<span style="left:725px;" class="th-item item-price">出版时间</span> 
							<span class="th-item item-op">书籍原价（元）</span>
						</div>
					</div>
					<div class="cart-bd">
						<div class="no-msg" style="font-size:17px;width:100%;height:100px;line-height:100px;text-align:center;">没有查询到相关记录，换个关键字查询</div>
						<ul class="book-list">
							<div class="mod books-group js-group-0" data-groupid="0">
								<div class="hd group-hd"></div>
								<div id="data_list" class="bd group-bd">
									
								</div>
								<li id="data_temp" style="display:none;" class="item-selected" style="background:#fff;">
									<div class="mod book-enable">
									
										<div class="bd book-cnt-wrap">
										
											<div class="book-item-wrap mod book-info-wrap">
												<input class="book-checkbox" type="checkbox" checked=""> 
												<label class="fake-checkbox " dataid="" data-num=""></label> 
												<a class="book-cover-link" target="_blank" href="/book/detail/" data-title data-id title=""> 
													<img class="book-cover" width="80" height="104" src="" data-img>
												</a>
												<div class="book-title-wrap" style="width:150px;">
													<a target="_blank" style="width: 140px;" class="book-title" href="/book/detail/" data-id data-name title=""></a> 
													<span class="book-author" style="line-height:25px;font-size:13px;" data-author>作者：</span>
												</div>
											</div>
											
											<div style="left:273px;" class="box mod price-wrap">
												<div class="media ">
													<p class="book-price one-line" data-isbn></p>
												</div>
											</div>
											
											<div style="left:473px;" class="box mod price-wrap">
												<div class="media ">
													<p class="book-price one-line" data-publish></p>

												</div>
											</div>
											
											<div class="box mod price-wrap">
												<div class="media ">
													<p class="book-price bookprice one-line" data-time></p>
												</div>
											</div>
											
											<a href="javascript:;" style="font-size:16px;color:#48B94C;width: 65px;" class="disable-link" alog-action="delete.book" data-price></a>
										</div>

									</div>
								</li>
							</div>
						</ul>
					</div>
					<div class="cart-ft">
						<div class="cart-ft-cnt box ext scroll-to-fixed-fixed" style="width:1160px!important">
							<div class="media">
								<a href="javascript:;" class="go-cashier-btn">出售</a>
							</div>
							<div class="content">
								<div class="cnt-item del-select-book-wrap">
									<a href="###" class="del-select-book">请选择你要出售的书籍信息</a>
								</div>
							</div>
						</div>
						<div class="spacer-fixed"
							style="display: block; width: 1090px; height: 54px; float: none;"></div>
					</div>
				</div>

			</div>
		</div>

	</div>
	<!--  -->
	<form id="search_form">
		<input type="hidden" name="innerPPAndRss" value="BOOKNAME#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKISBN#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKAUTHOR#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKPUBLISH#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKINTRO#like" />
		<input type="hidden" name="innerKeyOrValue" value=""/>
		<input type="hidden" name="pageNumber" value="1"/>
		<input type="hidden" name="orderBy" value=""/>
		<input type="hidden" name="key" value="" />
	</form>
	<!-- 搜索主体结束 -->
	<script src="/resource/front/js/jquery.js"></script>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<script src="<@front.js/>/business/sellbook.js"></script>
	<!-- 底部 -->
	<@front.rightBox/>
	<@front.bottom/>
