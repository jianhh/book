<#-- 前台网站 的界面 宏模块 -->

<!-- 绝对路径 -->
<#macro basePath><#if springMacroRequestContext.getContextPath()=="/"><#else>${(BASE_PATH)?default("")}</#if></#macro>

<#macro css><@base.basePath/>/resource/common/css</#macro>
<#macro js><@base.basePath/>/resource/common/js</#macro>
<#macro img><@base.basePath/>/resource/common/images</#macro>


<#-- 分页插件 -->	
<#macro pager>
	<ul class="pager-ul">
		<li id="pager_num" style="display:none;">
			<a>共<font color="red" id="totalCount"></font>条</a>
		</li>
		<li style="display:none;">
			<input type="number" value="" id="curr_page_num" style="width:50px;text-align:center;float:left" placeholder="页码"/>
		</li>
		<li style="cursor:pointer;display:none;">
			<a onclick="pageJump();"  class="btn btn-mini btn-success">跳转</a>
		</li>
		<li>
			<div class="pagerBar">
				<span id="ajaxPager" class="pager"></span>
			</div>
		</li>
		<li style="display:none;">
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

<#-- baseHeader -->
<#macro baseHeader title>
<!doctype html>
<html lang="en">
<head> 
	<meta charset="UTF-8">
	<title>${title}</title>
	<script type="text/javascript" src="<@common.js/>/jquery.min.js"></script>
	<script type="text/javascript">
 
		//设置用户的默认封面
		$("img.user").error(function(e){
			this.src="/resource/front/images/defaultImages/defUser.png";
		});
		
		//设置正方形的默认图片
		$("img.square").error(function(e){
			this.src="/resource/front/images/defaultImages/no_img300.jpg";
		});
		
		//设置长方形的默认图片
		$("img.long").error(function(e){
			this.src="/resource/front/images/defaultImages/no_img169.jpg";
		});
		var BASE = {
			basePath : "<@base.basePath/>"
		};
		
	</script>
</head>
<body>
</#macro>

<#-- baseFooter -->
<#macro baseFooter>
	</body>
</html>
</#macro>
