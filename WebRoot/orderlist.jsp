<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="/resource/front/css/orderlist.css">
<link rel="stylesheet" href="/resource/front/css/cart-min.css">
<script src="/resource/front/js/jquery.js"></script>
<script src="/resource/front/js/business/order.js"></script>
</head>
<body class="cart-taobao cart-lang-zh_CN">
	<div id="page" class="cl">
		<div id="content">
			<div class="cart-layout-TB ">
				<div id="J_Cart" class="cart">
					<div id="J_FilterBar" class="cart-filter-bar">
						<ul id="J_CartSwitch" class="switch-cart">
							<li class="btn-switch-cart switch-cart-0 current"><a
								href="https://cart.taobao.com/cart.htm?spm=a210c.3.4.2.6hXWAe"
								class="J_MakePoint" data-point="tbcart.8.35"><em>全部商品</em><span
									class="number">1</span><span class="pipe"></span></a></li>
						</ul>
						
						<div class="wrap-line">
							<div class="floater" style="width: 123px; left: -1px;"></div>
						</div>
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
								<div class="th th-info"><div class="td-inner">&nbsp;</div></div>
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
																		<input class="J_CheckBoxItem"
																			id="J_CheckBox_212574493785" type="checkbox"
																			name="items[]" value="212574493785"><label
																			for="J_CheckBox_212574493785">勾选商品</label>
																	</div>
																</div>
															</li>
															<li class="td td-item">
																<div class="td-inner">
																	<div class="item-pic J_ItemPic img-loaded">
																		<a href="//item.taobao.com/item.htm?id=527360185768"
																			target="_blank"
																			data-title="【现货速发】Xiaomi/小米 小米手机5 全网通标准版小米5八核手机"
																			class="J_MakePoint" data-point="tbcart.8.12"><img
																			src="https://img.alicdn.com/bao/uploaded/i4/827437070/TB2bNBtkFXXXXaTXpXXXXXXXXXX_!!827437070.jpg_80x80.jpg"
																			class="itempic J_ItemImg"></a>
																	</div>
																	<div class="item-info">
																		<div class="item-basic-info">
																			书名：看见
																		</div>
																		<div class="item-other-info">
																			<div class="promo-logos">作者：柴静</div>
																			<div class="item-icon-list clearfix">
																				出版社：机械工业出版社
																			</div>
																		</div>
																	</div>
																</div>
															</li>
															<li class="td td-info"></li>
															<li class="td td-price">
																<div class="td-inner" style="padding-top:30px;">
																	<div class="item-price price-promo-promo">
																		<div class="price-content">
																			<div class="price-line">
																				<em class="price-original">2598.00</em>
																			</div>
																			<div class="price-line">
																				<em class="J_Price price-now" tabindex="0">2438.00</em>
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
																	<em tabindex="0" class="J_ItemSum number">14628.00</em>
																	<div class="J_ItemLottery"></div>
																</div>
															</li>
															<li class="td td-op" style="line-height:120px;">
																<img style="margin-top:30px;width: 40px;cursor:pointer" src="/resource/front/images/trash.png" alt="删除" />
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
					<div id="J_FloatBarHolder" class="float-bar-holder">
						<div id="J_FloatBar" class="float-bar clearfix default"
							style="position: static;">
							<div id="J_SelectedItems"
								class="group-wrapper group-popup hidden" style="display: none;">
								<div id="J_SelectedItemsList" class="group-content"></div>
								<span class="arrow" style="left: 668px;"></span>
							</div>
							<div class="float-bar-wrapper">
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
</body>
</html>
