	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<link rel="stylesheet" href="<@operating.css />/treeSelect.css" />
	<link rel="stylesheet" href="<@base.js/>/ztree/zTreeStyle/zTreeStyle.css" />
	<link rel="stylesheet" href="<@base.js/>/z/zUpload.css" />
    <script type="text/javascript" src="<@base.js/>/ztree/jquery.ztree.core-3.5.js"></script>
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
		.commitopacity{position:absolute; width:100%; height:100%; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.8; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:990;}
	</style>
	
	<form id="saveForm" class="editor-form">
	
		<input type="hidden" name="buddhaMusicId" value="${(obj.buddhaMusicId)?if_exists}"/>
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					佛乐管理相关信息<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
      		 
			<table>
				<tr class="info">
					<td colspan="2">
						<span class="span-title">佛乐标题：</span>
						<input type="text" name="buddhaMusicTitle" onchange="showTitle();" id="buddhaMusicTitle" style="width: 364px;" value="${(obj.buddhaMusicTitle)?if_exists}" class="{required: true, maxlength:64, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入佛乐标题" title="佛乐标题"/>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2">
						<span class="span-title">演唱者：</span>
						<input type="text" name="buddhaMusicAuthor" onchange="showTitle();" id="buddhaMusicAuthor" style="width: 364px;" value="${(obj.buddhaMusicAuthor)?if_exists}" class="{required: true, maxlength:64, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="演唱者" title="演唱者"/>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				
				<tr class="info">
					<td colspan="2">
						<span class="span-title">歌词：</span>
						<textarea name="buddhaMusicLyrics" class="{required: true, messageDirection:'bottom'}" placeholder="歌词" title="歌词" style="resize:none;width:364px;">${(obj.buddhaMusicLyrics)?if_exists}</textarea>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info" style="height:auto;">
					<td colspan="2" style="vertical-align: middle;">
						<span class="span-title">佛乐文件：</span>
						<span style="width:129px;">
						<a class='btn btn-mini btn-info' title="重复上传将覆盖旧文件" id="buddhaMusicPath" >上传文件</a> 
						</span>
						<span  style="color:red;">*</span>
						
					</td>
				</tr>
				<tr class="info" style="height:auto;display:none">
					<td colspan="2" style="padding-left:110px;padding-top:10px;">
						<span>
							<p>
								<span><i class="icon-paper-clip"></i></span>
								<span><a id="buddhaMusicPath_a" href="${(obj.buddhaMusicPath)?if_exists}">${(obj.buddhaMusicTitle)?if_exists}</a></span>
                                <input id="buddhaMusicPath_b" type="hidden"  name='buddhaMusicPath' value="${(obj.buddhaMusicPath)?if_exists}" />       
                                <input type="hidden"  name='buddhaMusicPathOld' value="${(obj.buddhaMusicPath)?if_exists}" />  
                                <input id="buddhaMusicDurTime" type="hidden"  name='buddhaMusicDurTime' value="${(obj.buddhaMusicDurTime)?if_exists}" />  
                                
							</p>
						</span>
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
   	<script type="text/javascript" src="<@base.js/>/z/zUpload.js"></script>
	<script src="<@operating.js />/business/music.js"></script>
	<script type="text/javascript">
	
	 
 
 
	
	
	<!-- 树形结构开始  -->
		var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};
	<!-- 树形结构结束  -->
	
		initEditor();
		<#if obj?exists&&obj.buddhaMusicPath?exists>
		     $('#buddhaMusicPath_a').closest('.info').show();<!--找到父节点显示-->
		</#if>
		
		<!--  改变附件标题名称  -->
		function showTitle(){
			var title = $("#buddhaMusicTitle").val();
			$("#buddhaMusicPath_a").text(title);
		}
		
		$("#buddhaMusicPath").click(function(){
		
			var title = $("#buddhaMusicTitle").val();
			if(title != ""){
				$("#buddhaMusicPath_a").text(title);
			}
			else{
				$("#buddhaMusicPath_a").text("资源附件");
			}
			
		})
		
	</script>
		
<@operating.bottom />
		