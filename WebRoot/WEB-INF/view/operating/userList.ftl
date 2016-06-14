	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<link rel="stylesheet" href="<@base.js />/webuploader/webuploader.css" />
	<script src="<@base.js />/webuploader/webuploader.js"></script>
	<style>
	  	input.ace-switch[type="checkbox"]+.lbl::before{
	  	content: "启用\a0\a0\a0\a0未启用";	  	
	  	}
	</style>
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
									<input autocomplete="off" class="nav-search-input" name="innerKeyOrValue" value="" placeholder="这里输入关键字" type="text"  title="输入关键字">
									<i id="nav-search-icon" class="icon-search"></i>
								</span>
								<input type="hidden" name ="innerPPAndRss"  value="USERNAME#like"/>
								<input type="hidden" name ="innerPPAndRss"  value="EMAIL#like"/>
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
						<th class="center width-30px">
							<label><input id="zcheckbox" type="checkbox"><span class="lbl"></span></label>
						</th>
						<th>用户账号</th>
						<th>用户邮箱</th>
						<th>最近登录时间</th>
						<th>注册时间 </th>
						<th>是否启用</th>
						<th class="center" width="70">操作</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
				<!-- 开始循环 -->						
				</tbody>
				<!--循环模板-->
			 	<tr class="main_info" id="data_temp" style="display:none;">
					 	<td class='center'>
							<label>
								<input type="checkbox" name="userid" value=""/>
								<span class="lbl"></span>
							</label>
						</td>
						<td name="username"></td>
						<td name="email"></td>
						<td name="lastlogintime"></td>
						<td name="registertime"></td>
						<td class="center">
							<label>
								<input type="checkbox" class="ace-switch ace-switch-3" id="isEnable" value="{userid}"  name onclick="enable(this)" />
								<span class="lbl"></span>
							</label>								
						</td>
						<td class="center">
							<div class='hidden-phone btn-group' style="margin:0 auto;">
								<a class="btn btn-mini btn-info" title="预览" onclick="toView('{userid}');" name=""><i class="icon-eye-open"></i></a>
								<a class='btn btn-mini btn-info' title="编辑" onclick="editor('{userid}');" name><i class='icon-edit'></i></a>
							</div>
						</td>
	 			</tr>
	 			<!--循环模板-->
	 			
			</table>
		 
		<#-- 分页div -->	
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<a class='btn btn-small btn-success' title="编辑" onclick="editor();" name>新增</a>
				</tr>
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
<script src="<@operating.js />/business/user.js"></script>
<script>
	search();
</script>
<@operating.bottom />
