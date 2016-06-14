	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<link rel="stylesheet" href="<@base.js/>/ztree/zTreeStyle/zTreeStyle.css" />
    <script type="text/javascript" src="<@base.js/>/ztree/jquery.ztree.all-3.5.min.js"></script>
	<style type="text/css">
			.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
	
		<div id="" class="clearfix page-content">
			<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					学校新增栏目 <small><i class="icon-double-angle-right"></i> </small>
				</h1>					
	  		 </div>
	  		 <table style="margin-bottom:20px;">
				<tbody>
					<tr class="tr-paddingLeft10">
						<td>
							<span class="input-icon">
								<input autocomplete="off" style="width:200px;" id="keyword" class="nav-search-input" name="keyword" value="" placeholder="这里输入关键词" type="text"  title="输入关键词">
								<i id="nav-search-icon" class="icon-search"></i>
							</span>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div id="search_result_list" class="search_result_list" style="display:none;">
				
			</div>
	  		 
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
				
			</div>
			
			
		</div>
	
	<script type="text/javascript">
		var zNodes =[
		 
			<#if dicList?exists>
				<#list dicList as bd>
					<#if bd_index != 0>,</#if>
					{id:'${bd.dicid}', pId:'${bd.dicremark}',father:'${bd.dicremark}', name:'${bd.dicname}', open:<#if bd_index == 0>open<#else>false</#if>}
				</#list>
			</#if>
		 
 
		];
	</script>
	<!-- 引入 -->
	<script src="<@operating.js />/business/school.js"></script>
	<script type="text/javascript">
		//search(); //页面加载的时候就执行一次
		
		$('#searchForm').submit(function(){
			return false;
		});
	</script>
	<!-- 引入 -->
	
	<style type="text/css">
	li {list-style-type:none;}
	</style>
	<ul class="navigationTabs">
        <li><a></a></li>
        <li></li>
    </ul>
<@operating.bottom />
