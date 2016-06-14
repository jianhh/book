	<@front.top "个人中心-二手书"/>
	<link rel="stylesheet" href="<@front.css/>/personal.css" type="text/css">
	<link rel="stylesheet" href="<@front.css/>/personal/personal.css" type="text/css">
	<script src="/resource/front/js/jquery.js"></script>
	<link rel="stylesheet" href="<@base.js />/webuploader/webuploader.css" />
	<script src="<@base.js />/webuploader/webuploader.js"></script>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<script src="/resource/front/js/business/uploadheadpic.js"></script>
	<style>
	.webuploader-pick-hover {
		background: #14191e;
	}
	.webuploader-pick:hover{
		background: #14191e;
	}
	.webuploader-pick {
		display:block;
		background: #363d40;
		padding: 0px;
	}
	</style>
	<div id="main" style="display:block">
		<input type="hidden" id="data-index" value="${index?default('0')}" />
		<div class="wcontainer set-space-cont clearfix">
			<div class="setting-left l my-box-shadow" >
				<ul class="wrap-boxes">
					<li class="nav-active"><a href="javascript:;" class="onactive">个人资料</a></li>
					<li><a href="javascript:;">头像设置</a></li>
					<li><a href="javascript:;">邮箱验证</a></li>
					<li><a href="javascript:;">修改密码</a></li>
					<li><a href="javascript:;">我的收藏</a></li>
					<li><a href="/user/pocket">我的书袋(<font color="red">${bookpocketnum?default('0')}</font>)</a></li>
					<li><a href="javascript:;">购买记录</a></li>
					<li><a href="javascript:;">出售订单</a></li>
					<li><a href="javascript:;">正在寄售</a></li>
				</ul>
			</div>
			
			<div class="setting-right my-box-shadow">
				<div style="padding-left:20px;" class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">个人资料</span>
				    </div>
				</div>
				<div class="setting-right-wrap wrap-boxes settings">


					<div id="setting-profile" class="setting-wrap setting-profile">
						<form id="profile">
							<div class="wlfg-wrap">
								<label class="label-name" for="nick">昵称</label>
								<div class="rlf-group">
									<input type="text" name="username" id="nick" class="rlf-input rlf-input-nick"
										value="${tools.getBackUser().username}" placeholder="请输入昵称.">
									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for="phone">长号</label>
								<div class="rlf-group">
									<input type="text" value="${tools.getBackUser().phonenumber}" class="rlf-input" name="phonenumber" id="phone"/>
									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for="sphone">短号</label>
								<div class="rlf-group">
									<input type="text" class="rlf-input" value="${tools.getBackUser().shortphonenumber}" name="shortphonenumber" id="sphone"/>
									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for="school-select">学校</label>
								<div class="rlf-group profile-address">
									<select name="schoolid" onchange="selectCollege(this)" id="school-select">
										<#list sList as s>
											<option <#if s.dicid==tools.getBackUser().schoolid>selected</#if> value="${s.dicid}">${s.dicname}</option>
										</#list>
									</select> 
									<select id="college-select">
										<option value="">学院</option>
										<#list cList as c>
											<option <#if c.dicid==tools.getBackUser().collegeid>selected</#if> value="${c.dicid}">${c.dicname}</option>
										</#list>
									</select>

									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name">性别</label>
								<div class="rlf-group rlf-radio-group">
									<label><input type="radio" value="保密"
										name="sex">保密</label> <label><input type="radio"
										 value="男" checked="checked" name="sex">男</label>
									<label><input type="radio" value="女"
										name="sex">女</label>
								</div>
								<p class="rlf-tip-wrap"></p>
							</div>

							<div class="wlfg-wrap">
								<div class="rlf-group">
									<span id="profile-submit" hidefocus="true" aria-role="button"
										class="rlf-btn-green btn-block profile-btn">保存</span>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<!-- 头像设置开始 -->
			<div class="setting-right my-box-shadow">
				<div style="padding-left:20px;" class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">头像设置</span>
				    </div>
				</div>
				<div class="setting-right-wrap wrap-boxes settings">

					<div id="setting-avator"
						class="setting-wrap setting-avator clearfix">
						<div class="avator-img l">
							<div>
								<img id="userpic" class="userheadpic"
									src="${tools.getBackUser().userpicpath}"
									width="220" height="220">
							</div>
							<div>
								<input type="button" hidefocus="true" value="换一换"
									class="js-avator-try avator-try">
							</div>
						</div>
						<div class="avator-btn-group">
							<div id="avator-btns" class="avator-btn-inner">
								<div class="avator-btn-snswrap">
									<span class="l-sns-btn l-sns-qq" data-sns="qq"><i
										class="icon-qq"></i>从 QQ 帐号同步头像</span>
								</div>

								<div class="avator-btn-wrap">
									<form target="uploadtarget" action="postpic" method="post"
										enctype="multipart/form-data">
										<a href="javascript:void(0)" id="bookpic" 
											class="avator-btn-fake">上传头像</a>
									</form>
								</div>
							</div>
							<div class="avator-upload-wrap" style="display:none;">
								<span hidefocus="true" id="avator-btn-save" aria-role="button"
									class="rlf-btn-green btn-block">保存</span>
							</div>
						</div>

					</div>

				</div>
			</div>

			<!-- 头像设置结束-->

			<div class="setting-right my-box-shadow">
				<div style="padding-left:20px;" class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">邮箱验证</span>
				    </div>
				</div>
				<div class="setting-right-wrap wrap-boxes settings">

					<div class="setting-verify">
						<h1>当前邮箱</h1>
						<p>huihuanggungun@126.com</p>
						<button id="updateemail" data-mail="huihuanggungun@126.com"
							class="js-resetemail rlf-btn-blue">更改邮箱</button>
					</div>

				</div>
			</div>

			<!-- 修改密码开始 -->
			<div class="setting-right my-box-shadow">
				<div style="padding-left:20px;" class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">修改密码</span>
				    </div>
				</div>
				<div class="setting-right-wrap wrap-boxes settings">

					<div class="pwd-reset-wrap setting-resetpwd">
						<form method="post" id="resetpwdform">

							<div class="wlfg-wrap">
								<label class="label-name" for="">当前密码</label>
								<div class="rlf-group">
									<input type="password" name="oldpwd"
										class="rlf-input rlf-input-pwd" placeholder="请输入当前密码">
									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for="newpass">新密码</label>
								<div class="rlf-group">
									<input type="password" name="newpwd"
										id="newpass" class="rlf-input rlf-input-pwd"
										placeholder="请输入密码">
									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for="confirm">确认密码</label>
								<div class="rlf-group">
									<input type="password" name="confirmpwd" id="confirm"
										class="rlf-input rlf-input-pwd" placeholder="请输入密码">
									<p class="rlf-tip-wrap"></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for=""></label>
								<div class="rlf-group">
									<span id="resetpwd-btn-save" style="width: 360px;"
										 class="savePwd rlf-btn-green btn-block">保存</span>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 修改密码结束 -->
			
			<!-- 我的收藏 -->
			<div class="setting-right my-box-shadow">
			<div class="u-container">
                <div class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">我的收藏</span>
				    </div>
				    <div class="tool-right r">
				    	<div class="tool-all">
				            <span id="js-columall" class="tool-item">
				                <span>你共收藏了 <font color="#ff4400" class="totalBook"></font> 本书籍</span>
				        	</span>
				        </div>
				    </div>
				</div>
				
				<#-- 容器 -->
				<div id="data_list" class="js-course-list my-space-course study-tl">
					
        		</div>
        		<#-- 模板 -->
				<div id="data_temp" style="display:none;" class="clearfix tl-item">
		            <span class="time"><b data-year>2016</b><em data-other-date>03月19日</em></span>
           			<div class="course-list course-list-m">
               			<ul class="clearfix">
               				
                        </ul>
           			</div>
				</div>
				<#-- 模板2 -->
				<li id="data_temp_li" style="display:none;" class="course-one">
           			 <div class="course-list-img l">
               			 <a href="/book/detail/" data-id target="_blank"><img width="120" height="150" data-img src=""></a>
           			 </div>
           			 <div class="course-list-cont">
            			<h3 class="study-hd">
                  			<a href="/book/detail/" data-id data-name target="_blank"></a>
							<span class="i-new">(<span data-publish></span>　<span data-time></span>)</span>
			        	</h3>
                	    <div class="study-points" style="max-height:45px;overflow:hidden;">
							<span>简介</span>
							<span data-intro></span>
						</div>
                	    <div class="study-points">
							<span>原价</span><span data-price></span>
							<div class="blank10"></div>
							<span>作者</span><span data-author></span>
							<!--<a href="javascript:;" dataid target="_blank" class="buybook btn-my continute-btn">加入书袋</a>-->
							<a href="/book/sell" data-id target="_blank" class="sellbook btn-my continute-btn">出售</a>
						</div>
               		 </div>
				</li>
        		
				<!-- 分页 -->
				<div class="blank20"></div>
				<div class="qa-comment-page" style="text-align: center;">
					<div id="pager">
						
					</div>
				</div>
				<div class="blank30"></div>
	
			</div>	
			</div>
			
			<!-- 我的书袋 -->
			<div class="setting-right my-box-shadow"></div>
			<!-- 我的购买 -->
			<div class="setting-right my-box-shadow">
			<div class="u-container">
                <div class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">我的购买</span>
				    </div>
				    <div class="tool-right r">
				    	<div class="tool-all">
				            <span id="js-columall" class="tool-item">
				                <span>你共购买了 <font color="#ff4400" class="buytotalBook"></font> 本书籍</span>
				                <i style="cursor:pointer;" class="buyrecord tool-item icon icon-drop_down"></i>
				        	</span>
				        	<ul id="js-columbd" class="all-cont" style="display: none;">
                                <li><a data-id="0" href="javascript:;">所有购买</a></li>
                                <li><a data-id="7" href="javascript:;">购买成功</a></li>
                                <li><a data-id="7" href="javascript:;">购买失败</a></li>
                            </ul>
				        </div>
				    </div>
				</div>
				<#-- 购买内容 -->
				<#-- 容器 -->
				<div id="buy_list" class="js-course-list my-space-course study-tl">
					
        		</div>
        		<#-- 模板 -->
				<div id="buy_temp" style="display:none;" class="clearfix tl-item">
		            <span class="time"><b data-year>2016</b><em data-other-date>03月19日</em></span>
           			<div class="course-list course-list-m">
               			<ul class="clearfix">
               				
                        </ul>
           			</div>
				</div>
				<#-- 模板2 -->
				<li id="buy_temp_li" style="display:none;" class="course-one">
           			 <div class="course-list-img l">
               			 <a href="/book/detail/" data-id target="_blank"><img width="120" height="150" data-img src=""></a>
           			 </div>
           			 <div class="course-list-cont">
            			<h3 class="study-hd">
                  			<a href="/book/detail/" data-id data-name target="_blank"></a>
							<span class="i-new">(<span data-publish></span>　<span data-time></span>　<span data-author></span> 著)</span>
			        	</h3>
                	    <div class="study-points" style="max-height:45px;overflow:hidden;">
							<span>简介</span>
							<span data-intro></span>
						</div>
                	    <div class="study-points">
							<span>原价</span><span data-price></span>| <span> 实付</span><span data-nowprice></span>
							<div class="blank10"></div>
							<span>状态</span><span style="color:#ee524c;" data-status></span>
							<!--<a href="javascript:;" dataid target="_blank" class="buybook btn-my continute-btn">加入书袋</a>-->
							<a href="javascript:;" databuyid class="status12 confirmbook btn-my continute-btn">确认订单</a>
							<a href="/user/toPay?id=" data-buyid class="status0 btn-my continute-btn">立即付款</a>
							<a href="javascript:;" databuyid class="status0 cancelbook sellbook btn-hong hong-btn">取消订单</a>
						</div>
               		 </div>
				</li>
				
        		
				<!-- 分页 -->
				<div class="qa-comment-page" style="text-align: center;">
					<div id="buypager">
						
					</div>
				</div>
	
			</div>	
			</div>	
			<!-- 我的购买结束 -->
			<!-- 出售订单 -->
			<div class="setting-right my-box-shadow">
			<div class="u-container">
                <div class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">我的出售</span>
				    </div>
				    <div class="tool-right r">
				    	<div class="tool-all">
				            <span id="js-columall" class="tool-item">
				                <span>你共成功出售了 <font color="#ff4400" class="selltotalBook"></font> 本书籍</span>
				                <i style="cursor:pointer;" class="sellrecord tool-item icon icon-drop_down"></i>
				        	</span>
				        	<ul id="js-columcs" class="all-cont" style="display: none;">
                                <li><a onclick="sellsearch()" href="javascript:;">出售成功</a></li>
                                <li><a onclick="buysearch()" href="javascript:;">正在寄售</a></li>
                            </ul>
				        </div>
				    </div>
				</div>
				
				<#-- 出售内容-->
				<#-- 容器 -->
				<div id="order_list" class="js-course-list my-space-course study-tl">
					
        		</div>
        		<#-- 模板 -->
				<div id="order_temp" style="display:none;" class="clearfix tl-item">
		            <span class="time"><b data-year>2016</b><em data-other-date>03月19日</em></span>
           			<div class="course-list course-list-m">
               			<ul class="clearfix">
               				
                        </ul>
           			</div>
				</div>
				<#-- 模板2 -->
				<li id="order_temp_li" style="display:none;" class="course-one">
           			 <div class="course-list-img l">
               			 <a href="/book/detail/" data-id target="_blank"><img width="120" height="150" data-img src=""></a>
           			 </div>
           			 <div class="course-list-cont">
            			<h3 class="study-hd">
                  			<a href="/book/detail/" data-id data-name target="_blank"></a>
							<span class="i-new">(<span data-publish></span>　<span data-time></span> <span data-author></span> 著)</span>
			        	</h3>
                	    <div class="study-points" style="max-height:45px;overflow:hidden;">
							<span>收货地址</span>
							<span style="color:#53b856;" data-intro></span>
						</div>
                	    <div class="study-points">
							<span>买家</span><span class="userinfo" style="cursor:pointer;color:#53b856;" info data-user></span>
							| <span>　购买数量</span><span data-buynum></span>| <span>　已支付</span><span data-totalprice></span>
							<div class="blank10"></div>
							<span>状态</span><span data-status></span>|　<span>原价</span><span data-price></span>|　<span>现价</span><span data-nowprice></span>
							<!--<a href="javascript:;" dataid target="_blank" class="buybook btn-my continute-btn">加入书袋</a>-->
							<a href="javascript:;" data-buyid class="updatebuystatus btn-my continute-btn">修改状态</a>
						</div>
               		 </div>
				</li>
        		
				<!-- 分页 -->
				<div class="qa-comment-page" style="text-align: center;">
					<div id="sellpager">
						
					</div>
				</div>
	
			</div>	
			</div>	
			<!-- 出售订单结束 -->
			<!-- 正在寄售 -->
			<div class="setting-right my-box-shadow">
			<div class="u-container">
                <div class="c-tab clearfix">
					<div class="tool-left l">
				        <span class="sort-item active">我的出售</span>
				    </div>
				    <div class="tool-right r">
				    	<div class="tool-all">
				            <span id="js-columall" class="tool-item">
				                <span>你共出售了 <font color="#ff4400" class="selltotalBook"></font> 本书籍</span>
				                <i style="cursor:pointer;" class="sellrecord tool-item icon icon-drop_down"></i>
				        	</span>
				        	<ul id="js-columcs" class="all-cont" style="display: none;">
                                <li><a onclick="sellsearch()" href="javascript:;">出售成功</a></li>
                                <li><a onclick="buysearch()" href="javascript:;">正在寄售</a></li>
                            </ul>
				        </div>
				    </div>
				</div>
				
				<#-- 出售内容-->
				<#-- 容器 -->
				<div id="sell_list" class="js-course-list my-space-course study-tl">
					
        		</div>
        		<#-- 模板 -->
				<div id="sell_temp" style="display:none;" class="clearfix tl-item">
		            <span class="time"><b data-year>2016</b><em data-other-date>03月19日</em></span>
           			<div class="course-list course-list-m">
               			<ul class="clearfix">
               				
                        </ul>
           			</div>
				</div>
				<#-- 模板2 -->
				<li id="sell_temp_li" style="display:none;" class="course-one">
           			 <div class="course-list-img l">
               			 <a href="/book/detail/" data-id target="_blank"><img width="120" height="150" data-img src=""></a>
           			 </div>
           			 <div class="course-list-cont">
            			<h3 class="study-hd">
                  			<a href="/book/detail/" data-id data-name target="_blank"></a>
							<span class="i-new">(<span data-publish></span>　<span data-time></span>)</span>
			        	</h3>
                	    <div class="study-points" style="max-height:45px;overflow:hidden;">
							<span>简介</span>
							<span data-intro></span>
						</div>
                	    <div class="study-points">
							<span>作者</span><span data-author></span>|　<span>原价</span><span data-price></span>　|　<span>现价</span><span data-nowprice></span>
							<div class="blank10"></div>
							<span>出售数量</span><span data-num></span>|　<span>剩余数量</span><span data-sellnum></span>|　<span>状态</span><span data-status></span>
							<a href="javascript:;" data-sellid class="setbook btn-my continute-btn">修改库存</a>
							<a href="javascript:;" data-sellid class="putbook status0 btn-lv lv-btn hide">上架</a>
							<a href="javascript:;" data-sellid class="outbook status1 btn-hong hong-btn hide">下架</a>
						</div>
               		 </div>
				</li>
        		
				<!-- 分页 -->
				<div class="qa-comment-page" style="text-align: center;">
					<div id="sellpager">
						
					</div>
				</div>
	
			</div>	
			</div>	
			<!-- 正在寄售结束 -->
		</div>
	</div>


	<div class="msg-layer in" style="display:none;">
		<h3>身份验证</h3>
		<p>
			为保障用户身份安全，我们需要验证您的身份。<br>用户名：huihuanggungun@126.com
		</p>
		<input type="password" placeholder="请输入二手书的登录密码"> <a
			href="javascript:void(0)" class="btn-submit">确定</a>
		<button class="btn-close" type="button"></button>
	</div>
	
	<!-- 收藏  -->
	<form id="search_form">
		<input type="hidden" name="pageNumber" value="1" />
		<input type="hidden" name="pageSize" value="6" />
	</form>
	<!-- 出售 -->
	<form id="sell_form">
		<input type="hidden" name="pageNumber" value="1" />
		<input type="hidden" name="pageSize" value="6" />
	</form>
	<!-- 购买  -->
	<form id="buy_form">
		<input type="hidden" name="pageNumber" value="1" />
		<input type="hidden" name="pageSize" value="6" />
	</form>
	<!-- 搜索主体结束 -->
	<script src="/resource/front/js/jquery.js"></script>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<script src="/resource/front/js/business/personal.js"></script>
	<@front.rightBox/>
	<@front.bottom/>