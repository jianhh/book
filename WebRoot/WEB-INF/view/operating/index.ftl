	<@operating.top "二手书后台管理系统" />
	
	<link rel="stylesheet" href="<@operating.css />/login/ace-responsive.min.css" />
	<link rel="stylesheet" href="<@operating.css />/login/ace-skins.min.css" />
	<style type="text/css">
		.commitopacity{position:absolute; width:100%; height:100px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.8; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
	</style>
	<@operating.header />  <!-- 加载头部  -->

	<!-- 即时通讯 -->
	<#--
	<link rel="stylesheet" type="text/css" href="plugins/websocketInstantMsg/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="plugins/websocketInstantMsg/css/websocket.css" />
	<script type="text/javascript" src="plugins/websocketInstantMsg/ext4/ext-all-debug.js"></script>
	<script type="text/javascript" src="plugins/websocketInstantMsg/websocket.js"></script>
	-->
	<!-- 即时通讯 -->


	<!-- 页面顶部¨ -->
	<div id="websocket_button"></div>
	<div class="container-fluid" id="main-container">
		<a href="#" id="menu-toggler"><span></span></a>
		<!-- menu toggler -->

		<!-- 左侧菜单 -->
		<@operating.menu />
		
		<div id="main-content" class="clearfix">

			<div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
				<div class="commitopacity" id="bkbgjz"></div>
				<div style="padding-left: 70%;padding-top: 1px;">
					<div style="float: left;margin-top: 3px;"><img src="<@operating.img />/login/loadingi.gif" /> </div>
					<div style="margin-top: 5px;"><h4 class="lighter block red">&nbsp;加载中 ...</h4></div>
				</div>
			</div>

			<div>
				<iframe name="mainFrame" id="mainFrame" frameborder="0" src="<@operating.basePath />/operatingBack/main" style="margin:0 auto;width:100%;height:900px;"></iframe>
			</div>

			<!-- 换肤 -->
			<!--/#ace-settings-container-->

		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	<!-- basic scripts -->
		<!-- 引入 -->

		<script src="<@operating.js />/login/ace-elements.min.js"></script>
		<script src="<@operating.js />/login/ace.min.js"></script>

		<!-- 引入 -->
		<script type="text/javascript" src="<@operating.js />/login/menusf.js"></script>
		
		<!--引入属于此页面的js -->
		<script type="text/javascript" src="<@operating.js />/login/index.js"></script>
	<@operating.footer />
	
<@operating.bottom />