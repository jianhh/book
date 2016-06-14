	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<#-- 视频列表  -->
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
										<input autocomplete="off" id="inputInnerWord" class="nav-search-input" name="innerKeyOrValue" value="" placeholder="这里输入标题或作者" type="text" title="输入标题或作者">
										<i id="nav-search-icon" class="icon-search"></i>
									</span>
									<input type="hidden" name ="innerPPAndRss"  value="videoTitle#like"/>
									<input type="hidden" class="innerWord" name ="innerKeyOrValue"/>
									<input type="hidden" name ="innerPPAndRss"  value="videoIntr#like"/>
									<input type="hidden" class="innerWord" name ="innerKeyOrValue"/>
									<input type="hidden" name ="innerPPAndRss"  value="videoSpeakerName#like"/>
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
									<th>标题</th>
									<th>简介(点击展开)</th>
									<th>演讲者</th>
									<th>上传者</th>
									<th>发布时间</th>
									<th>视频截图路径</th>
									<th>视频大小</th>
									<th>浏览量</th>
									<th class="center" width="70">操作</th>
								</tr>
							</thead>
							
							<tbody id="data_list">
							<!-- 开始循环 -->		
							</tbody>
						 	<tr class="main_info" id="data_temp" style="display:none;">
								<td class='center'>
									<label>
										<input type="checkbox" name="videoId" value=""/>
										<span class="lbl"></span>
									</label>
								</td>
								<td name="videoTitle" width="100px" size="20" class="subStrNun"></td>
								<td onclick="changeHeight(this)" oh=""><div title="点击展开" name style="height: 17px;overflow: hidden;">{videoIntr}</div></td>
								<td name="videoIntr"></td>
								<td name="videoSpeakerName"></td>								
								<td name="videoUploadUserName"></td>
								<td name="videoAddTime"></td>
								<td heigth="1px"><img src="{videoPicPath}" name width="50px"/></td>
								<td name="videoSize"></td>
								<td name="videoClickCount"></td>
								<td class="center">
									<div class='hidden-phone btn-group' style="margin:0 auto;">
										<a class='btn btn-mini btn-info' title="编辑" onclick="editor('{videoId}')" name ><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="del('{videoId}','{videoTitle}');" name><i class='icon-trash'></i></a>
									</div>
								</td>
							</tr>
						</table>
						
					<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<a class="btn btn-small btn-success" onclick="editor();">新增</a>
				
									<a title="批量删除" class="btn btn-small btn-danger" onclick="delall('videoId')" >批量删除</a>
				
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
			<script src="<@operating.js />/business/video.js"></script>
			<script type="text/javascript" src="<@base.js />/select/search_select.js"></script>
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
