	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
    
	<form id="saveForm" class="editor-form">
		<input type="hidden" name="operaterId" value="${(obj.videoId)?if_exists}"/>
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					视频管理<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table>
				<tr class="info">
					<td>
						<span class="span-title">标题：</span>
						<input type="text" name="videoTitle" value="${(obj.videoTitle)?if_exists}" class="{required: true, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入视频标题"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">演讲者：</span>
						<input type="text" name="videoTitle" value="${(obj.videoSpeakerName)?if_exists}" class="{required: false, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入视频演讲者"  />
						<span  style="color:red;"></span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2">
						<span class="span-title">介绍：</span>
						<textarea name="videoIntr" class="{required: true, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入视频介绍"   style="resize:none;width:368px;">${(obj.videoIntr)?if_exists}</textarea>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2" style="padding:5px 0px;">
						<span class="span-title">视频截图：</span>
						<span>
								<a class='btn btn-mini btn-info' title="上传图片"  id="videoPicPath" >上传图片</a>
						</span>
						<span style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info" style="height:auto;">
					<td colspan="2" style="vertical-align: middle;">
						<span class="span-title">视频文件：</span>
						<span style="width:129px;">
						<a class='btn btn-mini btn-info' title="重复上传将覆盖旧文件" id="videoContentPath" >上传文件</a> 
						</span>
						<span  style="color:red;">*</span>
						
					</td>
				</tr>
			</table>
			<table style="margin:10px auto;">	
				<tr>
					<td style="text-align: center;">
						<a class="btn btn-mini btn-primary" onclick="save()">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="btn btn-mini btn-danger" onclick="cancel()">取消</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
	
	<div id="save_loading" class="center" style="display:none">
		<br/><br/><br/><br/>
		<img src="<@operating.img />/jiazai.gif" />
		<br/>
		<h4 class="lighter block green"></h4>
	</div>
	
	<!-- 引入 -->
	<script src="<@operating.js />/business/video.js"></script>
	<script type="text/javascript">
		initEditor();
		
	</script>
		
<@operating.bottom />
		