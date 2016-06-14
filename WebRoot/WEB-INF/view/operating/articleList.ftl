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
								<input type="hidden" name ="innerPPAndRss"  value="articletitle#like"/>
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
						<th class="center" onclick="selectAll()">
							<label><input id="zcheckbox" type="checkbox"><span class="lbl"></span></label>
						</th>
						<th>文章标题</th>
						<th>文章内容</th>
						<th>发布时间</th>
						<th>访问量</th>
						<th>文章图片</th>
						<th>发布人</th>
						<th class="center" width="70">操作</th>
					</tr>
				</thead>
				
				<tbody id="data_list">
									
				</tbody>
				 <tr class="main_info" id="data_temp" style="display:none;">
					<td class='center'>
						<label>
							<input type="checkbox" name="articleid" value=""/>
							<span class="lbl"></span>
						</label>
					</td>
					<td name="articletitle"></td>
					<td class="extend"><div title="点击展开" name="articlecontent" style="width:400px;height:50px;overflow: hidden;"></div></td>
					<td name="articletime"></td>
					<td name="articlepv"></td>
					<td class="fdPic"><img src="{picpath}" width="50px" name /></td>
					<td name="username"></td>
					<td class="center">
						<div class='hidden-phone btn-group' style="margin:0 auto;">
							<a class='btn btn-mini btn-info' title="编辑" onclick="editor('{articleid}')" name ><i class='icon-edit'></i></a>
							<a class='btn btn-mini btn-danger' title="删除" onclick="del('{articleid}','{articletitle}');" name><i class='icon-trash'></i></a>
						</div>
					</td>
				</tr>
				 
				 
			</table>
			
		<div class="page-header position-relative">
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<a class="btn btn-small btn-success" onclick="editor();">新增</a>
	
						<a title="批量删除" class="btn btn-small btn-danger" onclick="delall('articleid')" >批量删除</a>
	
					</td>
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
		
		<script src="<@operating.js />/business/article.js"></script>
		<script type="text/javascript">
			search();
			$('#search_form').submit(function(){
				return false;
			});
		</script>
	
<@operating.bottom />
