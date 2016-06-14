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
		<input type="hidden" name="bookid" value="${(obj.bookid)?if_exists}"/>
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					书本管理<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table  border= "1">
				<tr class="info">
					<td>
						<span class="span-title">书名：</span>
						<input type="text" name="bookname" class="{required: true, maxlength:24, messageDirection:'bottom', messageBgColor:'#AE81FF'}" value="${(obj.bookname)?if_exists}" placeholder="输入书名"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">isbn码：</span>
						<input type="text" name="bookisbn" value="${(obj.bookisbn)?if_exists}" placeholder="输入isbn码"  />
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">数量：</span>
						<input type="text" name="booknum" class="{required: true, maxlength:24, messageDirection:'bottom', messageBgColor:'#AE81FF'}" value="${(obj.booknum)?if_exists}" placeholder="输入数量"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">原价：</span>
						<input type="text" name="bookoldprice" value="${(obj.bookoldprice)?if_exists}" placeholder="输入原价"  />
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">出版时间：</span>
						<input type="text" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})" name="bookpublishtime" value="${(obj.bookpublishtime)?if_exists}" placeholder="输入时间"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">出版社：</span>
						<input type="text" name="bookpublish" value="${(obj.bookpublish)?if_exists}" placeholder="输入出版社"  />
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">页数：</span>
						<input type="text" name="bookpagesize" class="{required: true, maxlength:24, messageDirection:'bottom', messageBgColor:'#AE81FF'}" value="${(obj.bookpagesize)?if_exists}" placeholder="输入页数"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">备注：</span>
						<input type="text" name="bookremark" value="${(obj.bookremark)?if_exists}" placeholder="输入备注"  />
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">作者：</span>
						<input type="text" name="bookauthor" value="${(obj.bookauthor)?if_exists}" placeholder="输入作者"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">书本分类：</span>
						<select name="booktypeid" data-placeholder="请选择书本分类"  class="chzn-select {required: true}" style="vertical-align:top; margin-bottom:0 !important;">
							<option value="">请选择书本分类</option>
							<#list tList as t>
								<option value="${t.booktypeid}" <#if obj?if_exists && t.booktypeid==obj.booktypeid>selected</#if>>${t.booktypename}</option>
							</#list>
						</select>						
						<span style="color:red;">*</span>
					</td>
	 			</tr>
	 			<tr class="info">
	 				<td colspan="2">
	 					<span class="span-title">书本简介：</span>
	 				</td>
	 			</tr>
	 			<tr class="info">
					<td colspan="2">
						<div style="margin-left:110px;width=500px">
							<textarea name="bookintro" id="bookintr" style="height:200px;"  placeholder="输入书本内容" title="该书本内容" >${(obj.bookintro)?if_exists}</textarea>
						</div>
					</td>
	 			</tr>
	 			<tr class="info" style="height:auto;">
					<td style="vertical-align: middle;" colspan="2">
						<span class="span-title">书本封面：</span>
						<span style="width:129px;">
						<a title="重复上传将覆盖旧文件" id="bookpic" >上传图片</a> 
						</span>
						<span class="upload-tip">（分辨率:100px*80px &nbsp;&nbsp;图片类型:jpg、png&nbsp;&nbsp;大小:5M以内）</span>
					</td>
				</tr>
				<tr class="info" style="height:auto;">
					<td style="padding-left:110px;padding-top:10px;" id="js-face-reault" rowspan="3" colspan="1">
						<#if obj?if_exists>
							<div class="face-result-inner"><img src="${(obj.bookpicpath)?if_exists}" width="120"><span class="rm-face-reault">删除</span></div>
						</#if>
					</td>
					<input type="hidden" name="bookpic" value="${(obj.bookpic)?if_exists}" id="art-face" />
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
		
	<!-- 引入 -->
	<script type="text/javascript" src="<@base.js/>/laydate/laydate.js" charset="utf-8"></script>
	<script src="<@operating.js />/business/book.js"></script>
	<script type="text/javascript">
		initEditor();
   		var editor = new wangEditor('bookintr');
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
		