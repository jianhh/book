	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<#-- 运营用户列表  -->
			<div class="container-fluid" id="main-container">
			<div id="" class="clearfix page-content">
			  <div class="row-fluid">
				<div class="row-fluid">
					<!-- 检索  -->
					<form id="searchForm" style="margin-bottom:10px;">
						<table>
							<tbody><tr class="tr-paddingLeft10">
								<td>
									<span class="input-icon">
										<input autocomplete="off" class="nav-search-input" name="innerKeyOrValue" value="" placeholder="这里输入登录名/工号或者姓名或者电话或者邮箱" type="text" title="输入登录名/工号或者姓名或者电话或者邮箱">
										<i id="nav-search-icon" class="icon-search"></i>
									</span>
									<input type="hidden" name ="innerPPAndRss"  value="operaterAccount#like"/>
									<input type="hidden" name ="innerPPAndRss"  value="operaterRealName#like"/>
									<input type="hidden" name ="innerPPAndRss"  value="operaterTel#like"/>
									<input type="hidden" name ="innerPPAndRss"  value="operaterEmail#like"/>
									
								</td>
								<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
							</tr>
						</tbody></table>
					</form>
					<!-- 检索  -->
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center" onclick="selectAll()">
									<label><input id="zcheckbox" type="checkbox"><span class="lbl"></span></label>
									</th>
									<th>登录名/工号</th> 
									<th>姓名</th>
									<th>性别</th>
									<th>电话</th>
									<th>邮箱</th>									
									<th>是否启用</th>									
									<th class="center" width="70">操作</th>
								</tr>
							</thead>
							
							<tbody id="data_list">
							<!-- 开始循环 -->		
							</tbody>
						 	<tr class="main_info" id="data_temp" style="display:none;">
								<td class='center'>
									<label>
										<input type="checkbox" name="operaterId" value=""/>
										<span class="lbl"></span>
									</label>
								</td>
								<td name="operaterAccount"></td>
								<td name="operaterRealName"></td>
								<td name="operaterSex" replaceText="{0:'男',1:'女'}"></td>
								<td name="operaterTel"></td>
								<td name="operaterEmail"></td>								
								<td class="center">
									<label>
										<input type="checkbox" class="ace-switch ace-switch-3" value="{operaterId}" name onclick="enable(this)"/>
										<span class="lbl"></span>
									</label>
									<#-- 在这设定该账号是否启用 -->
								</td>								
								<td class="center">
									<div class='hidden-phone btn-group' style="margin:0 auto;">
										<a class='btn btn-mini btn-info' title="编辑" onclick="editor('{operaterId}');" name><i class='icon-edit'></i></a>
										<#if (operaterAccount)?exists && operaterAccount != "0001">
										<a class='btn btn-mini btn-danger' title="删除" onclick="del('{operaterId}','{operaterAccount}');" name><i class='icon-trash'></i></a>
										</#if>
									</div>
								</td>
							</tr>
						</table>
						
					<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<a class="btn btn-small btn-success" onclick="editor();">新增</a>
				
									<a title="批量删除" class="btn btn-small btn-danger" onclick="delall('operaterId');" >批量删除</a>
				
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
			</div>
					
			<!-- 引入 -->
			<script src="<@operating.js />/business/operater.js"></script>
			<script type="text/javascript">
				search(); //页面加载的时候就执行一次
				
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
