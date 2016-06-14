<@front.top "我的书袋-二手书" />
<link rel="stylesheet" href="/resource/front/css/orderlist.css">
<link rel="stylesheet" href="/resource/front/css/cart-min.css">
<script src="/resource/front/js/jquery.js"></script>
<script src="/resource/front/js/business/order.js"></script>
<style>
.c-tab {
  font-size: 14px;
  color: #787d82;
  height: 58px;
  line-height: 58px;
  border-bottom: 1px solid #d0d6d9;
}
.c-tab .sort-item {
margin-right: 53px;
display: inline-block;
color: #787d82;
}
.c-tab .tool-left .sort-item:hover, .c-tab .tool-left .active {
color: #ff4400;
font-size:17px;
font-weight:bold;
}
#page{
	background:#E9EAEB;
}
.cart{padding:0 20px;}
.moreseller-btn {
width: 100%;
height: 50px;
line-height: 40px;
background: #f5f5f5;
border-top: solid 1px #ddd;
border-bottom: solid 1px #ddd;
cursor: pointer;
}
body {
font: 12px/1.333 "\5FAE\8F6F\96C5\9ED1","Hiragino Sans GB",arial,helvetica,clean;
}
.book-cover {
display: block;
position: absolute;
top: 0;
left: 0;
width: 62px;
height: 81px;
overflow: hidden;
background: url(http://static.wenku.bdimg.com/static/ydcommon/widget/ui/read/yui/ebook/images/book_bg_648357e.png) -624px -297px no-repeat;
-webkit-border-radius: 5px;
border-radius: 5px;
-webkit-box-shadow: rgba(0,0,0,.15) 2px 1px 3px;
box-shadow: rgba(0,0,0,.15) 2px 1px 3px;
}
</style>
	<div id="page" class="cl">
		<div id="content" style="padding-top:50px;padding-bottom:30px;width:1200px;">
			<div class="cart-layout-TB my-box-shadow">
				<div id="J_Cart" class="cart">
					<div  class="c-tab clearfix">
						<div class="tool-left l">
					        <span class="sort-item active">全部商品</span>
					    </div>
					</div>
					<div id="J_FilterBar" class="cart-filter-bar">
					</div>
					<div id="J_CartMain" style="min-height: 180px;" class="cart-main">
						<div class="cart-table-th">
							<div class="wp">
								<div class="th th-chk">
									<div id="J_SelectAll1" class="select-all J_SelectAll">
										<div class="cart-checkbox">
											<input class="J_CheckBoxShop" id="J_SelectAllCbx1"
												type="checkbox" name="select-all" value="true"><label
												for="J_SelectAllCbx1">勾选购物车内所有商品</label>
										</div>
										&nbsp;&nbsp;全选
									</div>
								</div>
								<div class="th th-item">
									<div class="td-inner">商品信息</div>
								</div>
								<div class="th th-price">
									<div class="td-inner">单价（元）</div>
								</div>
								<div class="th th-amount">
									<div class="td-inner">数量</div>
								</div>
								<div class="th th-sum">
									<div class="td-inner">金额（元）</div>
								</div>
								<div class="th th-op">
									<div class="td-inner">操作</div>
								</div>
							</div>
						</div>
						<div id="J_OrderList">
							<div style="height: auto;">
								<div class="J_Order clearfix order-body   ">
									<div class="order-content">
										<div id="J_BundleList_s_827437070_1" class="item-list">
											<div id="J_Bundle_s_827437070_1_0"
												class="bundle  bundle-last ">
												<div id="J_ItemHolder_212574493785" class="item-holder">
													<div id="J_Item_212574493785"
														class="J_ItemBody item-body clearfix item-normal  first-item  last-item   ">
														<ul class="item-content clearfix">
															<li class="td td-chk">
																<div class="td-inner">
																	<div class="cart-checkbox ">
																		<input class="J_CheckBoxItem" type="checkbox" ><label>勾选商品</label>
																	</div>
																</div>
															</li>
															<li class="td td-item">
																<div class="td-inner">
																	<div class="item-pic J_ItemPic img-loaded">
																		<a href="" target="_blank" class="J_MakePoint">
																			<img src="/xhh/book/official/net/jingdong/2016/03/25/8a4fc97253a9d63f0153a9e2431900fd.jpg"
																			class="itempic J_ItemImg">
																		</a>
																	</div>
																	<div class="item-info" style="margin-top:10px;">
																		<span>书名：看见</span>
																		<div class="item-other-info">
																			<div class="promo-logos">作者：柴静</div>
																			<div class="item-icon-list clearfix">
																				出版社：机械工业出版社
																			</div>
																		</div>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="td-inner" style="padding-top:30px;">
																	<div class="item-price price-promo-promo">
																		<div class="price-content">
																			<div class="price-line">
																				<em class="price-original"></em>
																			</div>
																			<div class="price-line">
																				<em class="J_Price price-now" tabindex="0">18.00</em>
																			</div>
																		</div>
																	</div>
																</div>
															</li>
															<li class="td td-amount">
																<div class="td-inner" style="padding-top:50px;">
																	<div class="amount-wrapper ">
																		<div class="item-amount ">
																			<a href="#" class="J_Minus minus">-</a><input
																				type="text" value="6"
																				class="text text-amount J_ItemAmount" data-max="30"
																				data-now="6" autocomplete="off"><a href="#"
																				class="J_Plus plus">+</a>
																		</div>
																		<div class="amount-msg J_AmountMsg"></div>
																	</div>
																</div>
															</li>
															<li class="td td-sum">
																<div class="td-inner" style="padding-top:50px;">
																	<em tabindex="0" class="J_ItemSum number">18.00</em>
																	<div class="J_ItemLottery"></div>
																</div>
															</li>
															<li class="td td-op" style="line-height:120px;">
																<a href="">删除</a>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="J_FloatBarHolder" style="overflow:hidden;" class="float-bar-holder">
						<div id="J_FloatBar" class="float-bar clearfix default"
							style="position: static;">
							<div id="J_SelectedItems"
								class="group-wrapper group-popup hidden" style="display: none;">
								<div id="J_SelectedItemsList" class="group-content"></div>
								<span class="arrow" style="left: 668px;"></span>
							</div>
							<div class="moreseller-btn float-bar-wrapper">
								<div id="J_SelectAll2" class="select-all J_SelectAll">
									<div class="cart-checkbox">
										<input class="J_CheckBoxShop" id="J_SelectAllCbx2"
											type="checkbox" name="select-all" value="true"><label
											for="J_SelectAllCbx2">勾选购物车内所有商品</label>
									</div>
									&nbsp;全选
								</div>
								<div class="operations">
									<a href="#" hidefocus="true" class="J_DeleteSelected">删除</a>
								</div>
								<div class="float-bar-right">
									<div id="J_ShowSelectedItems" class="amount-sum">
										<span class="txt">已选商品</span><em id="J_SelectedItemsCount">0</em><span
											class="txt">件</span>
										<div class="arrow-box">
											<span class="selected-items-arrow"></span>
										</div>
									</div>
									<div id="J_CheckCOD" class="check-cod" style="display: none;">
										<span class="icon-cod"></span><span
											class="s-checkbox J_CheckCOD"></span>货到付款
									</div>
									<div class="pipe"></div>
									<div class="price-sum">
										<span class="txt">合计（不含运费）:</span><strong class="price">¥<em
											id="J_Total">0.00</em></strong>
									</div>
									<div class="btn-area">
										<a href="javascript:void(0)" id="J_Go"
											class="submit-btn submit-btn-disabled"
											aria-label="请注意如果没有选择宝贝，将无法结算"><span>结&nbsp;算</span><b></b></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<@front.rightBox/>
	<@front.bottom/>
