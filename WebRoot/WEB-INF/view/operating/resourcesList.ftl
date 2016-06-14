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
								<input type="hidden" name ="innerPPAndRss"  value="uploadnewfilename#like"/>
							</td>
							<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="orderBy" value=" uploadtime desc " />
			</form>
			<!-- 检索  -->
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>原名称</th>
						<th>新名称</th>
						<th>图片(点击放大)</th>
						<th>文件大小</th>
						<th>文件类型</th>
						<th>文件状态</th>
						<th class="center" width="70">操作</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
									
				</tbody>
				 <tr class="main_info" id="data_temp" style="display:none;">
					<td name="uploadoldfilename"></td>
					<td name="uploadnewfilename"></td>
					<td ><img width="50px" class="fdPic" src="{uploadfilepath}" name /></td>
					<td name="uploadtransize"></td>
					<td name="uploadmimetype"></td>
					<td name="uploadstatus" replaceText='{"0":"临时保存","1":"正式文件","2":"永远保留文件","3":"待删除","4":"立马删除"}'></td>
					<td class="center">
						<div class='hidden-phone btn-group' style="margin:0 auto;">
							<a class='btn btn-mini btn-info' title="预览" onclick="toView('{uploadid}')" name ><i class='icon-eye-open'></i></a>
						</div>
					</td>
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
		
		<script src="<@operating.js />/business/resources.js"></script>
		<script type="text/javascript">
			search();
			$('#search_form').submit(function(){
				return false;
			});
		</script>
	
<@operating.bottom />
