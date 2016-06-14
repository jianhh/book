	<@front.top "确认订单-二手书"/>
	<link href="<@front.css/>/order.css" rel="stylesheet">
	<style>
		body {
			font: 12px/1.333 "\5FAE\8F6F\96C5\9ED1","Hiragino Sans GB",arial,helvetica,clean;
		}
		.bookinfo{display: block;margin-top: 3px;margin-bottom: 7px;line-height: 16px;font-size: 12px;color: #555;}
		.bookinfo:hover{color: #56A857;text-decoration: none;}
		.bookauthor{display: block;line-height: 16px;font-size: 12px;color: #999;}
		.newaddress{color:#fff;width:100px;display: block;line-height:30px;height: 30px;font-size: 16px;font-family: '\65b9\6b63\6b63\9ed1\7b80\4f53','\5fae\8f6f\96c5\9ed1','\9ed1\4f53';background-color: #fb5d56;text-align: center;cursor: pointer;outline: 0;}
		.newaddress:hover{text-decoration: none;};
	</style>
	<div class="J_Page confirm-order my-box-shadow" style="min-height:600px;background: #fff;margin-top:40px;margin-bottom:20px;">
		<div class="buy-content ">

			<div id="holyshit11" style="padding-top: 20px;" class="address">
				<form name="addrForm" id="addrForm" action="#" data-spm="2">
					<h3>
						确认收货地址 <span class="manage-address">
						</span>
					</h3>
					<ul id="address-list" class="address-list">
						<#if list?if_exists>
							<#list list as add>
								<li class="J_Addr J_MakePoint clearfix J_DefaultAddr <#if add_index==0>selected</#if>">
									<i class="J_Marker marker iconfont icon-dituzuobiao"></i> 
									<span class="marker-tip">寄送至</span>
									<div class="address-info">
										<a href="javascript:void(0);" data-id="${add.addressid!}" class="J_Modify modify" >修改本地址</a> 
										<input name="address" class="J_MakePoint" type="radio" value="" <#if add_index==0>checked="checked"</#if>> 
										<label for="addrId_1199096219" class="user-address">
											<span class="addresspre">${add.pname!} ${add.cname!} ${add.coname!} ${add.detailaddress!}</span> (<span class="consig">${add.consignee!}</span>收) <em class="pnumber">${add.phonenumber!}</em>
										</label>
									</div>
								</li>
							</#list>
						</#if>
					</ul>
					<ul id="J_MoreAddress" class="address-list hidden">
					</ul>
					<div class="address-bar">
						<a href="javascript:void(0);" class="newaddress" style="color:#fff;" id="J_NewAddressBtn">使用新地址</a>
					</div>
				</form>
			</div>
			<!-- 需要改动-->
			<div class="buy-order-field">
				<h3>确认订单信息</h3>
				<div class="buy-th-line clearfix">
					<span class="buy-th-title">书籍</span> 
					<span class="buy-th-price">单价(元)</span>
					<span class="buy-th-quantity">数量</span> 
					<span class="buy-th-total">小计(元)</span>
					<span class="buy-th-promo">给卖家备注</span>
				</div>
				<div id="holyshit24" class="order">
					<#if orderList?if_exists>
					<#list orderList as order>
					<input type="hidden" name="buyid" value="${order.buyid!}"/>
					<div id="holyshit19" class="item clearfix">
						<div id="holyshit13" class="itemInfo item-title">
							<a class="bookinfo" target="_blank" href="/book/detail/${order.bookVo.bookid!}" title="${order.bookVo.bookname!}" class="itemInfo-link J_MakePoint"> 
								<span class="item-pic"> 
									<span> 
										<img width="80" height="104" class="itemInfo-pic" src="${order.bookVo.bookpicpath!}">
									</span>
								</span> 
								<span class="bookname itemInfo-title J_MakePoint" style="padding-left:10px;padding-top: 10px;width:245px;color:#555;">书名：${order.bookVo.bookname!} (${order.bookVo.bookpublish!})</span>
								<span class="bookauthor itemInfo-title J_MakePoint" style="padding-left:10px;margin-top: 10px;width:245px;">作者：${order.bookVo.bookauthor!}</span>
							</a>
							<p class="c2c-extraInfo-container promo-extraInfo"></p>
						</div>
						<div class="item-price">
							<span id="holyshit14" class="itemInfo price"> 
							<em class="style-normal-small-black J_ItemPrice">${order.buyprice!}</em>
							</span>
						</div>
						<div id="holyshit15" class="quantity item-quantity">
							<p>${order.buynumber!}</p>
						</div>
						
						<div id="holyshit17" class="itemPay item-total">
							<p class="itemPay-price price" style="text-align:center;">
								<em class="style-normal-bold-red J_ItemTotal ">${order.buytotalamount!}</em>
							</p>
						</div>
						<div id="holyshit16" class="promotion item-promo">
							<div class="promotion-sel">
								<div class="promotion-content-box">
								<textarea  class="buyremark" name="buyremark" style="overflow-x: hidden;" id="" cols="25" rows="3"></textarea>
								</div>
							</div>
						</div>
						<div class="item-form-desc white-line clearfix">
							<div class="item-form-eticketDesc"></div>
						</div>
						<div class="item-service blue-line"></div>
					</div>
					</#list>
					</#if>
				</div>
			</div>
		</div>
		<div class="buy-footer">
			<div class="order-go clearfix" data-spm="4">
				<div class="address-confirm clearfix">
					<div class="box">
						<div id="holyshit30" class="realPay" tabindex="0">
							<em class="t">实付款：</em> <span class="price g_price "> <span>¥</span>
								<em class="style-large-bold-red" id="J_ActualFee">${total?default("0.00")}</em>
							</span>
						</div>

						<div id="holyshit31" class="address">
							<div id="J_AddrBottomConfirm"></div>
							<p class="buy-footer-address">
								<span class="buy-line-title buy-line-title-type">寄送至：</span> <span
									class="buy-footer-address-detail J_BuyFooterAddressDetail">
									${address.pname!} ${address.cname!} ${address.coname!} ${address.detailaddress!}</span>
							</p>
							<p class="buy-footer-address">
								<span class="buy-line-title">收货人：</span> <span
									class="buy-footer-address-detail J_BuyFooterAddressRec">
									${address.consignee!} ${address.phonenumber!}</span>
							</p>
						</div>
					</div>
					<div id="holyshit32" class="submitOrder">
						<div class="go-btn-wrap">

							<a id="J_Go" href="javascript:void(0);" class="btn-go"
								tabindex="0" title="点击此按钮，提交订单">提交订单</a>
						</div>

						<div class="msg hidden" id="J_GlobalErr">
							<p class="error"></p>
						</div>
					</div>

					<a href="/user/pocket" class="return-cart J_MakePoint" target="_self">
						<i class="iconfont icon-fanhui"></i>返回购物车
					</a>
				</div>
			</div>
		</div>
	</div>
	<script src="<@front.js/>/business/order.js"></script>
	<!-- 底部 -->
	<@front.bottom/>