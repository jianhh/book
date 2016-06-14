<@front.top "我的书袋-二手书"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="/resource/front/css/pay/cart_page.css" />
	<div id="bd" style="background:#E9EAEB;" class="bd">

		<div class="bd-wrap" style="background:#fff;width:1200px!important;">
			<div class="body my-box-shadow" style="margin-top:40px;">

				<div id="cart-page-wrap" style="padding:0 20px;" class="cart-page">
					<div class="cart-hd">
						<div style="border-bottom:2px solid #EEA185;" class="cart-table-hd clearfix">
							<span class="th-item item-info">书籍信息</span> 
							<span style="left:325px;" class="th-item item-info">书籍单价</span> 
							<span style="left:525px;" class="th-item item-info">书籍数量</span> 
							<span style="left:725px;" class="th-item item-price">书本价格（元）</span> 
							<span class="th-item item-op">操作</span>
						</div>
					</div>
					<div class="cart-bd">
						<ul class="book-list">
							<form id="orderForm" action="/user/doOrder" method="post">
							<#if pocketList?if_exists>
							<#list pocketList as p>
							<div class="mod books-group js-group-0" data-groupid="0">
								<div class="hd group-hd"></div>
								<div class="bd group-bd">
									<li id="book_${p.sellid}" class="item-selected" style="background:#fff;">
										<div class="mod book-enable">
										
											<div class="bd book-cnt-wrap">
												<div class="book-item-wrap mod book-info-wrap">
													<input name="id" value="${p.sellid!}" class="book-checkbox" type="checkbox"> 
													<label class="fake-checkbox" data-id="${p.sellid!}" data-num="${p.booknum!}"></label> 
													<a class="book-cover-link" target="_blank" href="/book/detail/${p.bookid!}" title="${p.bookname!}"> 
														<img class="book-cover" src="${p.bookpicpath!}">
													</a>
													<div class="book-title-wrap">
														<a target="_blank" class="book-title"
															href="/book/detail/${p.bookid!}"
															title="${p.bookname!}">${p.bookname!}</a> <span class="book-author">作者：${p.bookauthor!}</span>

													</div>
												</div>
												
												<div style="left:273px;" class="box mod price-wrap">
													<div class="media ">
														<p class="book-price one-line">￥${p.bookoldprice!}</p>

													</div>
												</div>
												
												<div style="left:473px;" class="box mod price-wrap">
													<div class="media ">
														<p class="book-price one-line">${p.booknum!}</p>

													</div>
												</div>
												
												<div class="box mod price-wrap">
													<div class="media ">
														<p class="book-price bookprice one-line" data-price="${p.bookremark!}">￥${p.bookremark!}</p>

													</div>
												</div>
												
												<a href="###" data-id="${p.sellid}" class="disable-link deleteorder">删除</a>
											</div>

										</div>
									</li>
									
								</div>
							</div>
							</#list>
							</#if>
							</form>
						</ul>
					</div>
					<div class="cart-ft">
						<div class="cart-ft-cnt box ext scroll-to-fixed-fixed" style="width:1160px!important">
							<div class="media">
								<a href="javascript:;" class="go-cashier-btn">确认订单</a>
							</div>
							<div class="content">
								<div class="cnt-item select-all-book-wrap" style="cursor:pointer">
									<input id="is-select-all" class="is-select-all" type="checkbox">
									<label id="bottom-fake-checkbox"
										class="fake-checkbox bottom-checkbox"
										for="is-select-all"></label> <span class="ml5">全选</span>
								</div>
								<div class="cnt-item del-select-book-wrap">
									<a href="###" class="del-select-book">删除选中书籍</a>
								</div>
								<div class="cnt-item selected-book-count-wrap">
									已选<span class="rec-color js-selected-count">0</span>本书籍
								</div>
								<div class="cnt-item cart-ft-total-price">
									<p class="rec-color mt5">
										合计:￥<span class="cart-ft-total-price-txt">0.00</span>
									</p>
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
	<script src="<@front.js/>/business/order.js"></script>
	<!-- 底部 -->
	<@front.rightBox/>
	<@front.bottom/>
