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
									<input autocomplete="off" class="nav-search-input" value="" placeholder="这里输入关键字" type="text"  title="输入关键字">
									<i id="nav-search-icon" class="icon-search"></i>
								</span>
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
						<th>书名</th>
						<th>书籍封面</th>
						<th>出售用户</th>
						<th>出售数量 </th>
						<th>出售价格</th>
						<th>剩余数量</th>
						<th>出售时间</th>
						<th>书本状态</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
				<!-- 开始循环 -->						
				</tbody>
				<!--循环模板-->
			 	<tr class="main_info" id="data_temp" style="display:none;">
						<td name="bookname"></td>
						<td><img src="{bookpic}" name style="width:50px" alt="" /></td>
						<td name="username"></td>
						<td name="num"></td>
						<td name="price"></td>
						<td name="buytotalamount"></td>
						<td name="buytime"></td>
						<td name="buystatu" replaceText="{'1':'未发货','0':'未支付','2':'已发货','3':'交易成功'}"></td>
	 			</tr>
	 			<!--循环模板-->
	 			
			</table>
		 
		<#-- 分页div -->	
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
		<#-- 分页div -->
	</div>
 	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
<script src="<@operating.js />/business/buy.js"></script>
<script>
	search();
</script>
<@operating.bottom />
