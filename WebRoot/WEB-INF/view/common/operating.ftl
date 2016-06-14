<#-- 运营 系统的界面 宏模块 -->

<!-- 绝对路径 -->
<#macro basePath><#if springMacroRequestContext.getContextPath()=="/"><#else>${(BASE_PATH)?default("")}</#if></#macro>

<#macro ckeditorJs><#if springMacroRequestContext.getContextPath()=="/ckeditor"><#else><@operating.basePath/>/ckeditor</#if></#macro>
<#macro ckfinderJs><#if springMacroRequestContext.getContextPath()=="/ckfinder"><#else><@operating.basePath/>/ckfinder</#if></#macro>

<#macro css><@operating.basePath/>/resource/operating/css</#macro>
<#macro js><@operating.basePath/>/resource/operating/js</#macro>
<#macro img><@operating.basePath/>/resource/operating/images</#macro>

<#-- Top -->
<#macro top title="">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>${title}</title>
	<#--  css reset  -->
	<link rel='icon' href='<@front.img/>/title-logo.ico' type='image/x-ico' />
	<link rel="stylesheet" type="text/css" href="<@base.css/>/css-reset.css"/>	
   <!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="<@operating.css/>/font-awesome-ie7.min.css"/>
	<![endif]-->
	<#--  bootstrap 框架css -->
	<link rel="stylesheet" type="text/css" href="<@operating.css/>/bootstrap.min.css"/>	   <#-- bootstrap需要 -->
	<link rel="stylesheet" type="text/css" href="<@operating.css />/bootstrap-responsive.min.css"/><#-- bootstrap需要 -->
	
	<link rel="stylesheet" href="<@operating.css/>/chosen.css" />   <#-- bootstrap需要 -->
	<link rel="stylesheet" href="<@operating.css/>/base.css" />  
	<#--  字体图标  -->
	<link rel="stylesheet" type="text/css" href="<@operating.css />/login/ace.min.css" /><#-- bootstrap需要 -->
	<link rel="stylesheet" type="text/css" href="<@operating.css />/font-awesome.min.css"/><#-- bootstrap需要 -->
	<link rel="stylesheet" type="text/css" href="<@operating.css/>/font-awesome.css"/>	<#-- bootstrap需要 -->
	
	
	<#-- 列表样式  -->
    <link rel="stylesheet" media="screen" type="text/css" href="<@operating.css/>/custom.css" /><#-- Zoomimage图片放大组件 -->
    
    <link rel="stylesheet" href="<@operating.css />/login/ace-responsive.min.css" /> <#-- bootstrap需要 -->
	<link rel="stylesheet" href="<@operating.css />/login/ace-skins.min.css" />      <#-- bootstrap需要 -->
    <link rel="stylesheet" href="<@operating.css />/login/ace-responsive.min.css" /> <#-- bootstrap需要 -->
    
    <#-- 列表js -->
    <script type="text/javascript" src="<@operating.js/>/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<@base.js/>/layer/layer.js"></script>  		<#-- 弹出框 -->
    <script type="text/javascript" src="<@base.js/>/laydate/laydate.js"></script>  		<#-- 时间控件 -->
    
	<script type="text/javascript" src="<@operating.js/>/ace-elements.min.js"></script> <#-- bootstrap UI组件 -->
	 
	<script type="text/javascript" src="<@operating.js/>/jquery.tips.js"></script>   <#-- jquery提示,用于校验时提示 -->
	<script type="text/javascript" src="<@operating.js />/bootstrap.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/jquery.cookie.js"></script>
	
	<script type="text/javascript" src="<@operating.js />/chosen.jquery.min.js"></script><!-- 下拉框 -->
	
	<script type="text/javascript" src="<@base.js/>/jquery.validate.js"></script>
	<script type="text/javascript" src="<@base.js/>/jquery.validate.methods.js"></script>
	<script type="text/javascript" src="<@base.js/>/jquery.validate.cn.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<@base.css/>/pager.css"/>    			<#-- jquery 分页 -->
	<script type="text/javascript" src="<@base.js/>/jquery.pager.js"></script>          <#-- jquery 分页 -->
	
	<script type="text/javascript" src="<@base.js/>/json2.js"></script>                 <#-- json的序列化和反序列化方法,可以将一个json对象转换成json字符串,也可以将一个json字符串转换成一个json对象 -->

	<script type="text/javascript" src="<@base.js />/z/zUtils.js"></script>
	<script type="text/javascript" src="<@base.js />/z/zPager.js"></script>
	<script type="text/javascript" src="<@base.js />/z/zAction.js"></script>
 
	<script type="text/javascript" src="<@operating.js />/base.js"></script>     <#-- 全局化的设置:解决IE6透明PNG图片BUG -->
	
	<script type="text/javascript" src="<@operating.js />/patch.js"></script>     <#-- 全选框修复补丁 -->

 
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
 
</head>

<body>

</#macro>



<#-- 一般的页头 -->
<#macro header title="">
	<div class="navbar navbar-inverse">
		  <div class="navbar-inner">
		   <div class="container-fluid">
		      
			  <a class="brand" style="padding: 10px 20px 0px;">
			  	<small style="foat:left" class="small-img8">
			  		<span class="brandNmae"  style="float:left;padding:0 0 0 5px;line-height: 27px;">二手书后台管理系统</span>
			  	</small> 
			  </a>
			  
			  <span style="line-height:45px;height:45px; color:#fff; width:200px;font-size:14px;position: absolute; right:310px;">
			                 姓名:
				   ${tools.getBackUser().username}	  
      		  </span>
			  
			  <ul class="nav ace-nav pull-right">

					<li class="light-blue user-profile">
						<a class="user-menu dropdown-toggle" href="javascript:;" data-toggle="dropdown">
							<img alt="FH" src="<@operating.img/>/login/user.jpg" class="nav-user-photo" />
							<span id="user_info">
							</span>
							<i class="icon-caret-down"></i>
						</a>
						<ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
							<li><a onclick="editUserH();" style="cursor:pointer;"><i class="icon-user"></i> 修改资料</a></li>
						<!-- <li id="systemset"><a onclick="editSys();" style="cursor:pointer;"><i class="icon-cog"></i> 系统设置</a></li>
							<li id="productCode"><a onclick="productCode();" style="cursor:pointer;"><i class="icon-cogs"></i> 代码生成</a></li> -->
							<li class="divider"></li>
							<li><a href="<@operating.basePath/>/operatingBack/doLogout"><i class="icon-off"></i> 退出</a></li>
							<li><a href="<@operating.basePath/>/index"><i class="icon-home"></i> 前台首页</a></li>
						</ul>
					</li>
			  </ul><!--/.ace-nav-->
		   </div><!--/.container-fluid-->
		  </div><!--/.navbar-inner-->
		</div><!--/.navbar-->
	
	
		<!--引入属于此页面的js -->
		<script type="text/javascript" src="<@operating.js/>/head.js"></script>
	
</#macro>

<#-- Bottom -->
<#macro bottom>
	<div id="tong" style="display: none;text-align: center;"><img src=""></div>
	</body>
</html>
</#macro>


<#-- 菜单 -->
<#macro menu>

<div id="sidebar" class="menu-min">
	<!-- #sidebar-shortcuts -->
	
	<ul class="nav nav-list">
		<li class="active" id="fhindex">
		  <a href="<@operating.basePath/>/operatingBack"><i class="icon-dashboard"></i><span>后台首页</span></a>
		</li>
		
		<#if (tools.getBackUser().listPrivilegeVo)?exists>
			<#list tools.getBackUser().listPrivilegeVo as sc>
				<li id="column_${sc.privilegeid}">
				  <a style="cursor:pointer;" class="dropdown-toggle">
					<i class="${sc.privilegeicon}"></i>
					<span>${sc.privilegename}</span>
					<b class="arrow icon-angle-down"></b>
				  </a>
				  
					<#if (sc.list)?exists && sc.list?size gt 0>
						<ul style="display: none;" class="submenu">
							<#list sc.list as son>
								<li id="column_son_${son.privilegeid}">
									<a style="cursor:pointer;" target="mainFrame" 
									onclick="siMenu('${son.privilegeid}','column_${sc.privilegeid}','${son.privilegename}','<@operating.basePath />${son.privilegeurl}')">
										<i class="icon-double-angle-right"></i>${son.privilegename}
									</a>
								</li>
							</#list>
						</ul>
					</#if>
				</li>
			</#list>
		</#if>
	</ul><!--/.nav-list-->

	<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>

</div><!--/#sidebar-->
</#macro>

<#-- 一般的页脚 -->
<#macro footer>

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
