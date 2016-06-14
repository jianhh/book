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
								<input type="hidden" name ="innerPPAndRss"  value="roleName#like"/>
						 
					 
							</td>
							<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
							<!--<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i></a></td>
							<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>-->
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 检索  -->
		
	

		 
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<!--<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>-->
						<th>角色名称</th>
						<th>是否超级管理员</th>
						<th class="center" width="70">操作</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
							<!-- 开始循环 -->	
									
							</tbody>
						 	<tr class="main_info" id="data_temp" style="display:none;">
								<!--<td class='center'>
									<label><input type='checkbox' name='roleId' value="" /><span class="lbl"></span></label>
								</td>-->
								<td name="rolename"></td>
								<td name="roleisadmin" replaceText="{1:'是', 0:'否'}"></td>
								<td class="center">
									<div class='hidden-phone btn-group' style="margin:0 auto;">
										<#--
										<a class='btn btn-mini btn-warning' title="发送短信" onclick="sendSms('18788888888');"><i class='icon-envelope'></i></a>
									 	-->
										<a class='btn btn-mini btn-info' title="编辑" onclick="editor('{roleid}');" name><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="del('{roleid}','{rolename}');" name><i class='icon-trash'></i></a>
									</div>
								</td>
				 </tr>
			</table>
		 
			
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<a class="btn btn-small btn-success" onclick="editor();">新增</a>
	<#--
						<a title="批量删除" class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" ><i class='icon-trash'></i></a>
	-->
					</td>
				</tr>
			</table>
		</div>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<script src="<@operating.js />/business/role.js"></script>
		<script type="text/javascript">
			search();
			$('#search_form').submit(function(){
				return false;
			});
		</script>
	
<@operating.bottom />
