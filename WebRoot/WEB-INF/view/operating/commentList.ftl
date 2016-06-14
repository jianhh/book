	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
<div class="container-fluid" id="main-container">
	<div class="clearfix page-content">
	  <div class="row-fluid">
		<div class="row-fluid">
			<!-- 检索  -->
			<form id="search_form">
				<table style="margin-bottom:10px;">
					<tbody>
						<tr class="tr-paddingLeft10">
							<td>
								<span class="input-icon">
									<input autocomplete="off" class="nav-search-input" name="innerKeyOrValue" value="" placeholder="这里输入关键词" type="text"  title="输入关键词">
									<i id="nav-search-icon" class="icon-search"></i>
								</span>
								<input type="hidden" name ="innerPPAndRss"  value="commentcontent#like"/>
							</td>
							<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 检索  -->
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>评论人</th>
						<th>评论的书本</th>
						<th>评论内容</th>
						<th>评论时间</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
									
				</tbody>
				 <tr class="main_info" id="data_temp" style="display:none;">
					<td name="username"></td>
					<td name="bookname"></td>
					<td name="commentcontent"></td>
					<td name="commenttime"></td>
				</tr>
			</table>
			
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">
						<#-- 分页插件 -->				
							<@operating.pager />
						<#-- 分页插件 -->
				      	</div>
	       			</td>
				</tr>
			</table>
		</div>
	</div>
 	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<script src="<@operating.js />/business/comment.js"></script>
		<script type="text/javascript">
			search();
			$('#search_form').submit(function(){
				return false;
			});
		</script>
	
<@operating.bottom />
