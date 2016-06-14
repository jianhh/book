<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<link rel="stylesheet" href="/resource/front/css/pay/pay.css" />
<link rel="stylesheet" href="/resource/front/css/pay/paybook.css" />
</head>

<body>
	<div id="dialog-TANGRAM$9-container"
		class="mod ui-custiom-dialog-container"
		style="z-index: 9004; width: 715px; height: 616px; left: 296px; top: 0px;">
		<div id="iframe-cashier" class="inner dialog-inner">
			<div id="dialog-TANGRAM$9-head" class="hd dialog-head move-handler"></div>
			<div id="dialog-TANGRAM$9-content" class="bd dialog-content"
				style="display: block; height: 609px;">
				<div id="cashier-header" class="cashier-header">
					<div class="fl title-wrap">
						<span class="split mr10"></span><span class="text">购买图书</span>
					</div>
					<div class="fr fr-wrap">
						<div class="user-name">
							<span class="prefix">帐号：</span><span class="text">何小灰</span>
						</div>
						<a href="###" class="ic close dialog-close"></a>
					</div>
				</div>
				<div class="content-scroll">
					<div class="content-wrap">
						<div class="box book-wrap">
							<div class="media">购买图书：</div>
							<div class="content box book-name-cnt">
								<div class="media cover-media ">
									<img class="cover-img"
										src="http://hiphotos.baidu.com/doc/abpic/item/7dd98d1001e939010da2f2c97dec54e737d196a0.jpg">
								</div>
								<div class="content cover-cnt">
									<div class="book-names-wrap">
										<p class="book-name">看见</p>
									</div>
									<div class="book-counts ">共1本</div>
								</div>
							</div>
						</div>
						<div class="box price-wrap">
							<div class="media">图书总额：</div>
							<div class="content price-cnt">
								<span class="price-num">10.49</span><span class="price-suffix">元</span>
							</div>
						</div>
						<a href="###" id="use-voucher-btn" style="display: none;">确认抵扣</a>
						<div id="iframe-wrap" frameborder="0" style="display: block;">
							<div id="lbscashier-iframe" class="">
								<div class="channel-pay" data-payamount="1049"
									data-order="customerId=27&amp;service=cashier&amp;orderId=2016032700115736100&amp;orderCreateTime=1459073215&amp;orderExpireTime=1459074115&amp;deviceType=3&amp;payAmount=1049&amp;originalAmount=1049&amp;returnUrl=http%3A%2F%2Fyuedu.baidu.com%2Fcoupon%2Fapi%2Fqianbaoretradesuc&amp;notifyUrl=http%3A%2F%2Fyuedu.baidu.com%2Fcoupon%2Fapi%2Fqianbaotradesuc&amp;passuid=e3e7f0321b46de6f6db21a0e4&amp;title=%25E7%259C%258B%25E8%25A7%2581&amp;mobile=&amp;itemInfo=%5B%7B%22id%22%3A2678793171%2C%22name%22%3A%22%5Cu770b%5Cu89c1%22%2C%22number%22%3A1%2C%22price%22%3A1049%7D%5D&amp;tpl=10&amp;extData=%7B%22pass%22%3A%22%7B%5C%22offline_pay%5C%22%3A1%7D%22%2C%22spData%22%3A%22584abfL6TEkbtnlDyiJAxvkOHYbeMXzyFbqaBnVSufblyyi70IfsM5ecPA8Lz25ts7MakS6i8Xkjn7groFeATnAku35xfnW8NnAbSA2gTvwXjfShovaWSl0O%5C%2FvjwHxAuBAJgtAfAWUMHo6HZMhYIJPFGtpdy5X0jliNWJjiqITD4dFUbkAI%22%7D&amp;signType=1&amp;sign=e39b9d2988ab4a1efdbcc3acf7ac5f7e"
									data-customerid="27">
									<ul class="ca-tabs">
										<li data-target="bfb" data-code="BAIDU-PLATFORM-BAIFUBAO"
											class="current"><span class="ca-tabs-name">百度钱包</span> <img
											src="https://zhifu.baidu.com/static/images/discount_icons/tuijian_fanxian_17.png"
											class="ca-activity-icon"></li>
										<li data-target="bank" data-code="" class=""><span
											class="ca-tabs-name">网上银行</span></li>
										<li data-target="alipay" data-code="" class=""><span
											class="ca-tabs-name">支付宝</span></li>

									</ul>
									<div class="channel-wrapper">
										<div class="ca-tabs-panel hidden" id="wechat-panel"
											style="display: none;">
											<div class="ca-form-group clearfix">
												<label class="ca-label icon-label">支付方式：</label>

											</div>
											<div class="ca-form-group clearfix">
												<label class="ca-label">应付金额：</label> <span
													class="ca-form-option"> <span
													class="ca-spec-num ca-pay-amount"></span> <span
													class="ca-unit">元</span>
												</span>
											</div>
										</div>
										<div class="ca-tabs-panel hidden" id="bfb-panel"
											style="display: block;">
											<div class="ca-form-group clearfix">
												<label class="ca-label icon-label">支付方式：</label>

												<div class="bank-tab" id="easypay-first">
													<ul class="bank-tab-nav clearfix">
														<li data-target="xinyongka" class="selected"><a
															href="###"><span class="radio"></span>信用卡</a></li>
														<li data-target="chuxuka"><a href="###"><span
																class="radio"></span>储蓄卡</a></li>
													</ul>
													<div class="bank-tab-content">
														<div id="xinyongka-panel" class="bank-tab-panel clearfix">
															<ul class="channel-list choose-list-bank">
																<li data-code="BAIDU-EPC-ICBC" class="selected"><label
																	class="bank-icon BAIDU-EPC-ICBC"></label></li>
																<li data-code="BAIDU-EPC-CCB"><label
																	class="bank-icon BAIDU-EPC-CCB"></label></li>
																<li data-code="BAIDU-EPC-ABC"><label
																	class="bank-icon BAIDU-EPC-ABC"></label></li>
																<li data-code="BAIDU-EPC-PSBC"><label
																	class="bank-icon BAIDU-EPC-PSBC"></label></li>
																<li data-code="BAIDU-EPC-BOCM"><label
																	class="bank-icon BAIDU-EPC-BOCM"></label></li>
																<li data-code="BAIDU-EPC-CMB"><label
																	class="bank-icon BAIDU-EPC-CMB"></label></li>
																<li data-code="BAIDU-EPC-BOC"><label
																	class="bank-icon BAIDU-EPC-BOC"></label></li>
																<li data-code="BAIDU-EPC-CEB"><label
																	class="bank-icon BAIDU-EPC-CEB"></label></li>
																<li data-code="BAIDU-EPC-ECITIC"><label
																	class="bank-icon BAIDU-EPC-ECITIC"></label></li>
																<li data-code="BAIDU-EPC-SPDB"><label
																	class="bank-icon BAIDU-EPC-SPDB"></label></li>
																<li data-code="BAIDU-EPC-CMBC"><label
																	class="bank-icon BAIDU-EPC-CMBC"></label></li>
																<li data-code="BAIDU-EPC-CIB"><label
																	class="bank-icon BAIDU-EPC-CIB"></label></li>
																<li data-code="BAIDU-EPC-PAB"><label
																	class="bank-icon BAIDU-EPC-PAB"></label></li>
																<li data-code="BAIDU-EPC-GDB"><label
																	class="bank-icon BAIDU-EPC-GDB"></label></li>
																<li data-code="BAIDU-EPC-HXB"><label
																	class="bank-icon BAIDU-EPC-HXB"></label></li>
																<li data-code="BAIDU-EPC-BCCB"><label
																	class="bank-icon BAIDU-EPC-BCCB"></label></li>
															</ul>
														</div>
														<div id="chuxuka-panel" class="bank-tab-panel clearfix"
															style="display: none;">
															<ul class="channel-list choose-list-bank">
																<li data-code="BAIDU-EPD-ICBC" class="selected"><label
																	class="bank-icon BAIDU-EPD-ICBC"></label></li>
																<li data-code="BAIDU-EPD-CCB"><label
																	class="bank-icon BAIDU-EPD-CCB"></label></li>
																<li data-code="BAIDU-EPD-CMB"><label
																	class="bank-icon BAIDU-EPD-CMB"></label></li>
																<li data-code="BAIDU-EPD-BOC"><label
																	class="bank-icon BAIDU-EPD-BOC"></label></li>
																<li data-code="BAIDU-EPD-CEB"><label
																	class="bank-icon BAIDU-EPD-CEB"></label></li>
																<li data-code="BAIDU-EPD-CMBC"><label
																	class="bank-icon BAIDU-EPD-CMBC"></label></li>
																<li data-code="BAIDU-EPD-CIB"><label
																	class="bank-icon BAIDU-EPD-CIB"></label></li>
																<li data-code="BAIDU-EPD-GDB"><label
																	class="bank-icon BAIDU-EPD-GDB"></label></li>
																<li data-code="BAIDU-EPD-BCCB"><label
																	class="bank-icon BAIDU-EPD-BCCB"></label></li>
															</ul>
														</div>
													</div>
												</div>

											</div>
											<div class="ca-form-group clearfix">
												<label class="ca-label">应付金额：</label> <span
													class="ca-form-option"> <span
													class="ca-spec-num ca-pay-amount">10.49</span> <span
													class="ca-unit">元</span> <span
													class="ca-market-desc ca-market-desc-red">常年立返现金1%起，最高免单</span>
												</span>
											</div>
											<div class="ca-group ca-group-submit">
												<a href="#" target="_blank"
													class="fn-submit-btn btn-primary ">立即支付</a><span
													class="ca-btn-loading"></span>
											</div>
										</div>
										<div class="ca-tabs-panel hidden" id="bank-panel"
											style="display: none;">
											<div class="ca-form-group fn-normal-banks clearfix">
												<label class="ca-label icon-label">支付方式：</label>
												<ul class="ca-form-option choose-list-bank channel-list"
													id="bankPay">
													<li data-code="BAIDU-ICBC" class="selected"><label
														class="bank-icon BAIDU-ICBC"></label></li>
													<li data-code="BAIDU-CCB"><label
														class="bank-icon BAIDU-CCB"></label></li>
													<li data-code="BAIDU-ABC"><label
														class="bank-icon BAIDU-ABC"></label></li>
													<li data-code="BAIDU-PSBC"><label
														class="bank-icon BAIDU-PSBC"></label></li>
													<li data-code="BAIDU-BOCM"><label
														class="bank-icon BAIDU-BOCM"></label></li>
													<li data-code="BAIDU-CMB"><label
														class="bank-icon BAIDU-CMB"></label></li>
													<li data-code="BAIDU-BOC"><label
														class="bank-icon BAIDU-BOC"></label></li>
													<li data-code="BAIDU-CEB"><label
														class="bank-icon BAIDU-CEB"></label></li>
													<li data-code="BAIDU-ECITIC"><label
														class="bank-icon BAIDU-ECITIC"></label></li>
													<li data-code="BAIDU-SPDB" class="hidden bank-more"><label
														class="bank-icon BAIDU-SPDB"></label></li>
													<li data-code="BAIDU-CMBC" class="hidden bank-more"><label
														class="bank-icon BAIDU-CMBC"></label></li>
													<li data-code="BAIDU-CIB" class="hidden bank-more"><label
														class="bank-icon BAIDU-CIB"></label></li>
													<li data-code="BAIDU-PAB" class="hidden bank-more"><label
														class="bank-icon BAIDU-PAB"></label></li>
													<li data-code="BAIDU-GDB" class="hidden bank-more"><label
														class="bank-icon BAIDU-GDB"></label></li>
													<li data-code="BAIDU-HXB" class="hidden bank-more"><label
														class="bank-icon BAIDU-HXB"></label></li>
													<li data-code="BAIDU-BCCB" class="hidden bank-more"><label
														class="bank-icon BAIDU-BCCB"></label></li>
													<li class="choose-other-bank"><a> + 更多银行</a></li>
												</ul>

											</div>
											<div class="ca-form-group clearfix">
												<label class="ca-label">应付金额：</label> <span
													class="ca-form-option"> <span
													class="ca-spec-num ca-pay-amount">10.49</span> <span
													class="ca-unit">元</span>
												</span>
											</div>
											<div class="ca-group ca-group-submit">
												<a href="#" target="_blank"
													class="fn-submit-btn btn-primary ">立即支付</a><span
													class="ca-btn-loading"></span>
											</div>
										</div>
										<div class="ca-tabs-panel hidden" id="alipay-panel"
											style="display: none;">
											<div class="ca-form-group clearfix">
												<label class="ca-label icon-label">支付方式：</label>
												<div class="ca-form-group clearfix">
													<ul class="ca-form-option choose-list-icon channel-list">
														<li class="selected" data-activity=""
															data-code="BAIDU-PLATFORM-ALIPAY"><label
															class="option-icon BAIDU-PLATFORM-ALIPAY"></label></li>
													</ul>
												</div>
												<div class="qrcode-wrapper">
													<iframe id="aliQRIframe" class="js-lbscashierIframe"
														frameborder="0" scrolling="no" src="about:blank"></iframe>
													<div class="qrcode-err hidden">
														<span class="qrcode-err-desc"></span>
													</div>
												</div>


											</div>
											<div class="ca-form-group clearfix">
												<label class="ca-label">应付金额：</label> <span
													class="ca-form-option"> <span
													class="ca-spec-num ca-pay-amount">10.49</span> <span
													class="ca-unit">元</span>
												</span>
											</div>
											<div class="ca-group ca-group-submit">
												<a href="#" target="_blank"
													class="fn-submit-btn btn-primary ">立即支付</a><span
													class="ca-btn-loading"></span>
											</div>
										</div>

									</div>
								</div>
								<div class="ca-channeltips" style="display: none;">
									<h4>温馨提示</h4>
									<ul class="fn-general-tip">
										<li class="gametip" style="display: none;">您可以通过点卡官网、报刊亭、网吧、拉卡拉或电话订购等方式购买游戏点卡进行充值</li>
										<li class="gametip" style="display: none;">请选择与您使用的点卡相同的面额支付</li>
										<li class="gametip" style="display: none;">不能使用指定游戏专属充值卡支付</li>
										<li class="mobiletip-dx" style="display: none;">支持全国通用的电信卡（卡号19位，密码18位，卡号第四位为1）</li>
										<li class="mobiletip-lt" style="display: none;">
											支持全国通用的联通卡（卡号15位，密码19位）。面额： 20,30,50,100 元</li>
										<li class="mobiletip" style="display: none;">
											您可以通过营业厅、报刊亭、超市、便利店等方式购买手机充值卡进行充值</li>
										<li class="mobiletip" style="display: none;">请选择与您使用的手机卡相同的面额支付</li>
										<li class="gameyeepaytip" style="display: none;">您可以通过点卡官网、报刊亭、网吧、拉卡拉或电话订购等方式购买游戏点卡进行充值</li>
										<li class="gameyeepaytip" style="display: none;">请选择与您使用的点卡相同的面额支付</li>
										<li class="gameyeepaytip" style="display: none;">不能使用指定游戏专属充值卡支付</li>
										<li class="mobileyeepaytip-dx" style="display: none;">支持全国通用的电信卡（卡号19位，密码18位，卡号第四位为1）</li>
										<li class="mobileyeepaytip-lt" style="display: none;">
											支持全国通用的联通卡（卡号15位，密码19位）。面额： 20,30,50,100 元</li>
										<li class="mobileyeepaytip" style="display: none;">
											您可以通过营业厅、报刊亭、超市、便利店等方式购买手机充值卡进行充值</li>
										<li class="mobileyeepaytip" style="display: none;">请选择与您使用的手机卡相同的面额支付</li>
										<li class="smstip" style="display: none;">如充值过程中出现异常，请先查询是否扣款成功。</li>
										<li class="smstip" style="display: none;">暂不支持虚拟运营商话费支付。</li>
										<li class="smspaytip" style="display: none;">
											营商收取其中50%手续费，如：商品1元需支付话费 2元，短信费用0.1元/条，由运营商收取</li>
										<li class="smspaytip" style="display: none;">
											目前仅支持移动、联通号码短信支付</li>
									</ul>
								</div>
								<div class="ca-copy">百度钱包支持</div>
								<form action="/proxy/req/pay" method="post" target="_blank"
									id="lbscashierPayForm"></form>
							</div>
						</div>
					</div>
				</div>
				<div class="inner-pop-wrap hide">
					<div class="inner-pop-bg"></div>
					<div class="inner-pop-hack">
						<div class="inner-pop-cnt">
							<div class="success-tips"></div>
							<div class="voucher-failed-tips">
								<a href="###" class="ic close js-reload-page"></a>
								<div class="cnt-wrap">
									<p class="tips-txt">抵扣失败，请重新支付！</p>
									<a href="###" class="yui-btn repay-btn js-reload-page">重新支付</a>
								</div>
							</div>
							<div class="waiting-tips">
								<a href="###" class="ic close js-reload-page"></a>
								<div class="cnt-wrap">
									<p class="tips-txt mb15">请在新打开的页面完成支付，支付成功前请不要关闭或刷新此窗口</p>
									<div class="btn-wrap">
										<a href="###"
											class="yui-btn yui-btn-white3 repay-btn js-reload-page">失败，重新支付</a>
										<a href="###"
											class="yui-btn yui-btn-green success-btn js-paycheck">支付成功</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="dialog-TANGRAM$9-buttons" class="ft pv20 dialog-footer"
				style="display: block;"></div>
		</div>
	</div>
</body>
</html>
