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
								<input type="hidden" name ="innerPPAndRss"  value="spotsTitle#like"/>
						 
					 
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
						<th>景区名称</th>
						<th>封面图片</th>
						<th>简介</th>
						<th>地址</th>
						<th class="center" width="70">操作</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
									
				</tbody>
				 	<tr class="main_info" id="data_temp" style="display:none;">
						 
		 			</tr>
			</table>
		 
			
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<a class="btn btn-small btn-success" onclick="editor();">新增</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
 	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
 
		
		<script src="<@operating.js />/business/spots.js"></script>
		<script type="text/javascript">
			search();
			$('#search_form').submit(function(){
				return false;
			});
		</script>
	
<@operating.bottom />
