<!-- 绝对路径 -->
<#macro basePath><#if springMacroRequestContext.getContextPath()=="/"><#else>${(BASE_PATH)?default("")}</#if></#macro>

<#macro ckeditorJs><#if springMacroRequestContext.getContextPath()=="/ckeditor"><#else><@operating.basePath/>/ckeditor</#if></#macro>
<#macro ckfinderJs><#if springMacroRequestContext.getContextPath()=="/ckfinder"><#else><@operating.basePath/>/ckfinder</#if></#macro>

<#macro css><@front.basePath/>/resource/front/css</#macro>
<#macro js><@front.basePath/>/resource/front/js</#macro>
<#macro img><@front.basePath/>/resource/front/images</#macro>

<#-- Top -->
<#macro simpleTop title="">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title}</title>
    <!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<@operating.css/>/font-awesome-ie7.min.css"/>
	<![endif]-->
    <#-- 列表js -->
    <script type="text/javascript" src="<@front.js/>/jquery.min.js"></script>
    <script type="text/javascript" src="<@base.js/>/layer/layer.js"></script>  		<#-- 弹出框 -->
    <script type="text/javascript" src="<@base.js/>/laydate/laydate.js"></script>  		<#-- 时间控件 -->
    
	<script type="text/javascript" src="<@operating.js />/base.js"></script>     <#-- 全局化的设置:解决IE6透明PNG图片BUG -->
	
	<script type="text/javascript">
		var BASE = {
			basePath : "<@front.basePath/>"
		};
		layer.config({
			extend: [
					'extend/layer.ext.js', 'skin/layer.ext.css'
			]
		});
	</script>
 
</head>
<body>
</#macro>

<#macro top title="" >
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>${title}</title>
<link rel='icon' href='<@front.img/>/title-logo.ico' type='image/x-ico' />
<link rel="stylesheet" href="<@front.css/>/saved_resource.css" type="text/css">
<link rel="stylesheet" href="<@front.css/>/global.min.css" />
<link rel="stylesheet" href="<@front.css/>/b.css" />
<link rel="stylesheet" href="<@front.css/>/index.css" />
<link rel="stylesheet" href="<@front.css/>/buttom.css" />
<link rel="stylesheet" href="<@front.css/>/default.css" />
<link rel="stylesheet" type="text/css" href="/resource/front/css/booklist/bookcommon.css">
<script type="text/javascript" src="<@front.js/>/jquery.js"></script>
<script type="text/javascript" src="<@front.js/>/scroll.js"></script>
<script type="text/javascript" src="<@base.js/>/layer/layer.js"></script> 
<script type="text/javascript">
	var BASE = {
		basePath : "<@operating.basePath/>"
	};
	layer.config({
		extend: [
				'extend/layer.ext.js', 'skin/layer.ext.css'
		]
	});
</script>
<style>
	.selected1{
		background:#353D40;
		color:#fff;
	}
</style>
</head>
<body id="index">

	<div id="header">
		<div class="page-container" id="nav">
			<div id="logo" class="logo">
				<a href="/index" target="_self" class="hide-text">二手书</a>
			</div>
			<div class="g-menu-mini l">
				<a href="#" class="menu-ctrl"> <i
					class="icon-menu"></i>
				</a>
				<ul class="nav-item l">
					<li><a href="/index" <#if title=="首页-二手书">class="selected1"</#if>>首页</a></li>
					<li><a href="/book" <#if title=="所有书籍-二手书">class="selected1"</#if>>书籍</a></li>
					<li><a href="/search/index" <#if title=="搜索-二手书">class="selected1"</#if>>淘书</a></li>
					<li><a href="/index/ad" <#if title=="公告-二手书">class="selected1"</#if>>公告</a></li>
					<li><a href="/user/sell" <#if title=="出售书籍-二手书">class="selected1"</#if>>出售</a></li>
				</ul>
			</div>
			<div id="login-area">
				<#if tools.getBackUser()==null>
					<ul class="header-unlogin clearfix">
		                <li class="header-signin">
		                    <a href="<@front.basePath/>/index/toLogin" id="js-signin-btn">登录</a>
		                </li>
		                <li class="header-signup">
		                    <a href="<@front.basePath/>/index/toReg" id="js-signup-btn">注册</a>
		                </li>
		            </ul>
				<#else>
		            <ul class="clearfix logined">
		        	    <!--<li class="my_message">
		                    <a href="/user/personal?index=9" title="我的消息">
		                        <span class="msg_icon" style="display: none;"></span>
		                        <i class="icon-mail"></i>
		                        <span style="display: none;">我的消息</span>
		                    </a>
		                </li>-->
		                <li class="set_btn user-card-box">
		                    <a id="header-avator" class="user-card-item" action-type="my_menu" href="/user/personal" target="_self"><img class="user_circular userheadpic" src="${tools.getBackUser().userpicpath}" width="40" height="40">
		                        <i class="myspace_remind" style="display: none;"></i>
		                        <span style="display: none;">动态提醒</span>
		                    </a>
		                    <div class="g-user-card">
		                        <div class="card-inner">
		                            <div class="card-top">
		                                <a href="/user/personal"><img class="user_circular userheadpic" src="${tools.getBackUser().userpicpath}" class="l"></a>
		                                <a href="/user/personal"><span class="name text-ellipsis">${tools.getBackUser().username}</span></a>
		                                <p class="meta">最近登录:${tools.getBackUser().lastlogintime?default('您这是第一次登陆')}</p>
		                    
		                                <a href="javascript:;" class="icon-set setup"></a>
		                            </div>
		                            
		                            <div class="card-sets clearfix">
		                                <a href="/user/personal" class="l mr30">个人中心</a>
		                                <a href="/user/pocket"  class="l">我的书袋(${bookpocketnum?default('0')})</a>
		                                <a href="/index/doLogout" class="r">退出</a>
		                            </div>
		                        </div>
		                        <i class="card-arr"></i>
		                    </div>
		                </li>
		            </ul>
		        </#if>
       		</div>

			<#--
			<div class="search-warp clearfix"
				style="min-width: 32px; height: 60px;">
				<a href="/search/index">
					<div class="showhide-search" data-show="no">
						<i class="icon-search"></i>
					</div>
				</a>
			</div>
			-->
		</div>
	</div>

</#macro>

<#-- Bottom -->
<#macro bottom>
<!-- 底部 -->
	<div class="blank"></div>
	<div class="footer bg-white idx-minwidth">
		<div class="container">
			<div class="footer-wrap idx-width">
				<div class="footer-sns">
					<a href=""
						class="footer-sns-weibo hide-text" target="_blank" title="新浪微博">新浪微博</a>
					<a href="javascript:void(0);" class="footer-sns-weixin"
						target="_blank" title="微信"> <i
						class="footer-sns-weixin-expand"></i>
					</a> <a href=""
						class="footer-sns-qqweibo hide-text" target="_blank" title="腾讯微博">腾讯微博</a>
					<a href=""
						class="footer-sns-qzone hide-text" target="_blank" title="QQ空间">QQ空间</a>
				</div>
			</div>
			<div class="footer-link">
				<a href="" title="关于我们">关于我们</a> 
				<a href="http://sighttp.qq.com/authd?IDKEY=7ea7beeade0459411803b273cd144274f5a3ba9849e3eafb" title="联系我们">联系我们</a> 
				<a href="" title="意见反馈">意见反馈</a>
			</div>
			<div class="friendly-link">
				<span>友情链接：</span> <a href="http://hao.360.cn/" target="_blank"
					title="360导航">360导航</a> <a href="http://www.php-z.com/"
					target="_blank" title="PHP站中文网">PHP站中文网</a> <a
					href="http://www.hao123.com/" target="_blank" title="hao123">hao123</a>
				<a href="/operatingBack/toLogin" target="_blank" title="后台管理">后台管理</a>
				<a href="http://www.gdpubook.com/" target="_blank" title="广药二手书">广药二手书-有间实验室</a>
			</div>
			<div class="footer-copyright">Copyright © 2016 All
				Rights Reserved</div>
		</div>
	</div>
	<script>
		$(function(){
			$(".user_circular").error(function(){
			  $(this).attr("src","/resource/common/images/defuser.png");
			});
			$(".defbook").error(function(){
			  $(this).attr("src","/resource/common/images/default_book.jpg");
			});
		});
		if($("#bookpocketnum").attr("data-pocket-num")>0){
			$(".bookpocket").css({"background-color":"#53b856","color":"#fff"});
			$(".bookpocket .ic").css("display","none");
			$("#bookpocketnum").css("display","block");
		}
		function goTop(){
			$('html, body').animate({scrollTop:0}, 'slow');
		}
	</script>
</body>
</html>
</#macro>

<#-- Bottom -->
<#macro simpleBottom>

	</body>
</html>
</#macro>

<#-- 右侧栏 -->
<#macro rightBox>
<style>
#side-bar a{
font: 12px/1.333 "\5FAE\8F6F\96C5\9ED1","Hiragino Sans GB",arial,helvetica,clean;
}
</style>
<div id="side-bar" style="/* right: 25px; */ display: block;" class="">
	<div class="btn-wrap mb5">
		<a href="###" class="side-bar-item gotop" onclick="goTop()"><span class="ic"></span></a>
		<a href="/user/pocket" class="side-bar-item weibo bookpocket"
			target="_blank"><span class="ic"></span><span id="bookpocketnum" data-pocket-num="${bookpocketnum?default('0')}" class="text-tip">书袋(${bookpocketnum?default('0')})
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
		<a href="/feedback" target="_blank"
			class="feedback-remind-link  log-xsend" data-logxsend="[2, 200206]"></a>
	</div>
</div>
</#macro>


<#-- 分页插件 -->	
<#macro pager>
	<ul class="pager-ul">
		<li>
			<a>共<font color="red" id="totalCount"></font>条</a>
		</li>
		<li>
			<input type="number" value="" id="curr_page_num" style="width:50px;text-align:center;float:left" placeholder="页码"/>
		</li>
		<li style="cursor:pointer;">
			<a onclick="pageJump();"  class="btn btn-mini btn-success">跳转</a>
		</li>
		
		<li>
			<div class="pagerBar">
				<span id="ajaxPager" class="pager"></span>
			</div>
		</li>
		
		<li>
			<select title='显示条数' style="width:55px;float:left;" onchange="changePageSize()" id="pageSize">
				
				<option value='10'>10</option>
				<option value='20'>20</option>
				<option value='30'>30</option>
				<option value='40'>40</option>
				<option value='50'>50</option>
				<option value='60'>60</option>
				<option value='70'>70</option>
				<option value='80'>80</option>
				<option value='90'>90</option>
				<option value='99'>99</option>
			</select>
		</li>
	</ul>
	
	<script type="text/javascript">
		// 改变每页显示条数
		function changePageSize() {
		
		    $('#curr_page_num').val('1');
		
		    search(1);
		}
		
		// 翻页跳转
		function pageJump() {
		    var toPaggeVlue = document.getElementById("curr_page_num").value;
		    if (toPaggeVlue == '') {
			document.getElementById("curr_page_num").value = 1;
			return;
		    }
		    if (isNaN(Number(toPaggeVlue))) {
			document.getElementById("curr_page_num").value = 1;
			return;
		    }
		    search(toPaggeVlue);
		}
	</script>
</#macro>
