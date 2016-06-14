	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<link rel="stylesheet" href="<@base.js />/webuploader/webuploader.css" />
	<script src="<@base.js />/webuploader/webuploader.js"></script>
	<link rel="stylesheet" href="<@base.js/>/wangEditor/css/wangEditor.min.css" />
	<script src="<@base.js/>/wangEditor/js/wangEditor.min.js"></script>
    <style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
	<form id="saveForm" class="editor-form">
		<input type="hidden" name="articleid" value="${(obj.articleid)?if_exists}"/>
		<input type="hidden" name="articleUserId" value="${(obj.articleUserId)?if_exists}" />
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					文章相关信息<small><i class="icon-double-angle-right"></i> </small>
				</h1>
      		 </div>
						<span class="span-title">标题：</span>
						<input type="text" style="width:364px;" name="articletitle" value="${(obj.articletitle)?if_exists}" class="{required: true, maxlength:128, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入文章标题" title="文章标题"/>
						<br />
						<span class="span-title">文章内容：</span>
						<div style="margin-top:10px;margin-left:110px;width=500px" >
							<div  id="articlecontent" style="height:200px;"  placeholder="输入文章内容" title="该文章内容" >${(obj.articlecontent)?if_exists}</div>
						</div>
						<span class="span-title">文章封面：</span>
						<span style="width:129px;">
						<a title="重复上传将覆盖旧文件" id="userpic" >上传图片</a> 
						</span>
						<span class="upload-tip">（分辨率:1500px*350px &nbsp;&nbsp;图片类型:jpg、png&nbsp;&nbsp;大小:5M以内）</span>
						<div style="padding-left:110px;padding-top:10px;" id="js-face-reault">
							<#if obj?if_exists>
								<div class="face-result-inner"><img src="${(obj.picpath)?if_exists}" width="120"><span class="rm-face-reault">删除</span></div>
							</#if>
						</div>
						<input type="hidden" name="articlepic" value="${(obj.articlepic)?if_exists}" id="art-face" />
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
		<div style="display:none;">
			<textarea id="contentid" name="articlecontent"></textarea>
		</div>
	</form>
	
	<!-- 引入 -->
	<script src="<@operating.js />/business/article.js"></script>
	<script type="text/javascript">
		initEditor();
   		var editor = new wangEditor('articlecontent');
   		// 自定义菜单
	    editor.config.menus = [
	        'source',
	        'bold',
	        'underline',
	        'italic',
	        'fontfamily',
       		'fontsize',
       		'|',
       		'img',
       		'link',
	        'unlink',
	        'table',
	        'emotion',
	        'eraser',
	        'forecolor',
	        'bgcolor',
	        'fullscreen'
	     ];
   		editor.config.uploadImgUrl = '/fileUpload';
   		editor.create();
	</script>		
<@operating.bottom />
		